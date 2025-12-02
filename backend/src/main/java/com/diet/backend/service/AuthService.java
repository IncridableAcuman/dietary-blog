package com.diet.backend.service;

import com.diet.backend.dto.AuthResponse;
import com.diet.backend.dto.RegisterRequest;
import com.diet.backend.entity.User;
import com.diet.backend.enums.Role;
import com.diet.backend.repository.UserRepository;
import com.diet.backend.util.CookieUtil;
import com.diet.backend.util.JwtUtil;
import com.diet.backend.util.MailUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
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
}
