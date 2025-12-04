package com.diet.backend.service;

import com.diet.backend.dto.*;
import com.diet.backend.entity.User;
import com.diet.backend.enums.Role;
import com.diet.backend.exception.NotFoundException;
import com.diet.backend.repository.UserRepository;
import com.diet.backend.util.CookieUtil;
import com.diet.backend.util.JwtUtil;
import com.diet.backend.util.MailUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final MailUtil mailUtil;
    private final CookieUtil cookieUtil;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthResponse register(RegisterRequest request, HttpServletResponse response) throws BadRequestException {
        if (userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new BadRequestException("User already exist");
        }
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        user.setAvatar("https://github.com/shadcn.png");
        user=userRepository.save(user);
        String accessToken = jwtUtil.generateAccessToken(user);
        String refreshToken = jwtUtil.refreshToken(user);
        tokenService.create(user,refreshToken);
        cookieUtil.addCookie(refreshToken,response);
        return new AuthResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getAvatar(),
                accessToken
        );
    }
    @Transactional
    public AuthResponse login(AuthRequest request,HttpServletResponse response) throws BadRequestException {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new NotFoundException("User not found"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new BadRequestException("Invalid password");
        }
        String accessToken = jwtUtil.generateAccessToken(user);
        String refreshToken = jwtUtil.refreshToken(user);
        tokenService.regenerateToken(user,refreshToken);
        cookieUtil.addCookie(refreshToken,response);
        return new AuthResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getAvatar(),
                accessToken
        );
    }
    @Transactional
    public AuthResponse refresh(String refreshToken,HttpServletResponse response) throws BadRequestException {
        if (refreshToken == null || refreshToken.isEmpty()){
            throw new BadRequestException("Invalid token");
        }
        String username = jwtUtil.extractSubject(refreshToken);
        if (!jwtUtil.validateToken(refreshToken,username)){
            throw new BadRequestException("Invalid token");
        }
        User user = userRepository.findByUsername(username).orElseThrow(()->new NotFoundException("User not found"));
        String accessToken = jwtUtil.generateAccessToken(user);
        String newRefreshToken = jwtUtil.refreshToken(user);
        tokenService.regenerateToken(user,newRefreshToken);
        cookieUtil.addCookie(newRefreshToken,response);
        return new AuthResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getAvatar(),
                accessToken
        );
    }
    @Transactional
    public void logout(String refreshToken,HttpServletResponse response) throws BadRequestException {
        if (refreshToken == null || refreshToken.isEmpty()){
            throw new BadRequestException("Invalid token");
        }
        String username = jwtUtil.extractSubject(refreshToken);
        if (!jwtUtil.validateToken(refreshToken,username)){
            throw new BadRequestException("Invalid token");
        }
        User user = userRepository.findByUsername(username).orElseThrow(()->new NotFoundException("User not found"));
        tokenService.removeToken(user);
        cookieUtil.clearCookie(response);
    }
    public UserResponse getMe(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        User user = (User) authentication.getPrincipal();
        assert user != null;
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getAvatar()
        );
    }
    @Transactional
    public void forgotPassword(ForgotPasswordRequest request){
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()->new NotFoundException("User not found"));
        String token = jwtUtil.generateAccessToken(user);
        String url = "http://localhost:5173/reset-password?token="+token;
        mailUtil.sendMail(user.getEmail(),"Reset Password",url);
    }
    @Transactional
    public void updatePassword(ResetPasswordRequest request) throws BadRequestException {
        if (request.getToken()==null || request.getToken().isEmpty()){
            throw new BadRequestException("Invalid token");
        }
        String username = jwtUtil.extractSubject(request.getToken());
        if (!jwtUtil.validateToken(request.getToken(),username)){
            throw new BadRequestException("Invalid token");
        }
        User user = userRepository.findByUsername(username).orElseThrow(()->new NotFoundException("User not found"));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }
}
