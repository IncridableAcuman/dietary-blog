package com.diet.backend.service;

import com.diet.backend.entity.Token;
import com.diet.backend.entity.User;
import com.diet.backend.exception.BadRequestException;
import com.diet.backend.exception.NotFoundException;
import com.diet.backend.repository.TokenRepository;
import com.diet.backend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public void create(User user, String refreshToken){
        Token token =new Token();
        token.setUser(user);
        token.setRefreshToken(refreshToken);
        token.setExpiryDate(LocalDateTime.now().plusDays(7));
        tokenRepository.save(token);
    }
    @Transactional
    public void regenerateToken(User user,String refreshToken){
        Optional<Token> existToken = tokenRepository.findByUser(user);
        existToken.ifPresentOrElse(token -> {
            token.setRefreshToken(refreshToken);
            token.setExpiryDate(LocalDateTime.now().plusDays(7));
            tokenRepository.save(token);
        },
                ()->create(user,refreshToken)
        );
    }
    @Transactional
    public void removeToken(User user){
        Token token = tokenRepository.findByUser(user).orElseThrow(()-> new NotFoundException("User not found"));
        tokenRepository.delete(token);
    }

    public void validateToken(String token,String username){
        if (token == null || token.isEmpty()){
            throw new BadRequestException("Invalid token");
        }
        if (!jwtUtil.validateToken(token,username)){
            throw new BadRequestException("Invalid or Expired Token");
        }
    }
    public Map<String,String> getTokens(User user){
        Map<String,String> tokens = new HashMap<>();
        String accessToken = jwtUtil.generateAccessToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);
        tokens.put("accessToken",accessToken);
        tokens.put("refreshToken",refreshToken);
        return tokens;
    }
    public String extractUsername(String token){
        return jwtUtil.extractSubject(token);
    }
}
