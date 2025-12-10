package com.diet.backend.service;

import com.diet.backend.dto.*;
import com.diet.backend.entity.Token;
import com.diet.backend.entity.User;
import com.diet.backend.enums.Role;
import com.diet.backend.exception.BadRequestException;
import com.diet.backend.exception.NotFoundException;
import com.diet.backend.repository.UserRepository;
import com.diet.backend.util.CookieUtil;
import com.diet.backend.util.MailUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final MailUtil mailUtil;
    private final CookieUtil cookieUtil;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final OnlyUserService onlyUserService;

    public AuthResponse authResponse(User user,String accessToken){
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
    public AuthResponse register(RegisterRequest request, HttpServletResponse response) throws BadRequestException {
        if (findUserByEmail(request.getEmail()).isPresent()){
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
        user=onlyUserService.saveUser(user);
        String accessToken = tokenService.getTokens(user).get("accessToken");
        String refreshToken = tokenService.getTokens(user).get("refreshToken");
        Token token = tokenService.saveRefreshToken(user,refreshToken);
        cookieUtil.addCookie(token.getRefreshToken(),response);
        return authResponse(user,accessToken);
    }
    @Transactional
    public AuthResponse login(AuthRequest request,HttpServletResponse response) throws BadRequestException {
        User user = findUserByEmail(request.getEmail()).orElseThrow(()-> new NotFoundException("User not found"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new BadRequestException("Invalid password");
        }
        String accessToken = tokenService.getTokens(user).get("accessToken");
        String refreshToken = tokenService.getTokens(user).get("refreshToken");
        tokenService.saveRefreshToken(user,refreshToken);
        cookieUtil.addCookie(refreshToken,response);
        return authResponse(user,accessToken);
    }
    @Transactional
    public AuthResponse refresh(String refreshToken,HttpServletResponse response) throws BadRequestException {
        String username = tokenService.extractUsername(refreshToken);
        tokenService.validateToken(refreshToken,username);
        User user = findUserByUsername(username);
        String newAccessToken = tokenService.getTokens(user).get("accessToken");
        String newRefreshToken = tokenService.getTokens(user).get("refreshToken");
        Token token = tokenService.saveRefreshToken(user,newRefreshToken);
        cookieUtil.addCookie(token.getRefreshToken(),response);
        return authResponse(user,newAccessToken);
    }
    @Transactional
    public void logout(String refreshToken,HttpServletResponse response)  {
        String username = tokenService.extractUsername(refreshToken);
        tokenService.validateToken(refreshToken,username);
        User user = findUserByUsername(username);
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
        User user = findUserByEmail(request.getEmail()).orElseThrow(()->new NotFoundException("User not found"));
        String token = tokenService.getTokens(user).get("accessToken");
        String url = "http://localhost:5173/reset-password?token="+token;
        mailUtil.sendMail(user.getEmail(),"Reset Password",url);
    }
    @Transactional
    public void updatePassword(ResetPasswordRequest request) throws BadRequestException {
        String username = tokenService.extractUsername(request.getToken());
        tokenService.validateToken(request.getToken(),username);
        User user = findUserByUsername(username);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        onlyUserService.saveUser(user);
    }
    public User findUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(()->new NotFoundException("User not found"));
    }
    public Optional<User> findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

}
