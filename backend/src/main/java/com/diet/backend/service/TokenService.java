package com.diet.backend.service;

import com.diet.backend.entity.Token;
import com.diet.backend.entity.User;
import com.diet.backend.exception.NotFoundException;
import com.diet.backend.exception.UnAuthorizeException;
import com.diet.backend.repository.TokenRepository;
import com.diet.backend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
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
    public Token saveRefreshToken(User user,String refreshToken){
        Optional<Token> optional = tokenRepository.findByUser(user);
        Token token = optional.orElseGet(Token::new);
        token.setUser(user.getId());
        token.setRefreshToken(refreshToken);
        token.setExpiryDate(LocalDateTime.now().plusSeconds(jwtUtil.getRefreshTime()/1000));
        return saveUser(token);
    }
    @Transactional
    public void removeToken(User user){
        Token token = tokenRepository.findByUser(user).orElseThrow(()-> new NotFoundException("User not found"));
        tokenRepository.delete(token);
    }

    public void validateToken(String token,String username){
        if (token == null || token.isEmpty()){
            throw new UnAuthorizeException("Invalid token");
        }
        if (!jwtUtil.validateToken(token,username)){
            throw new UnAuthorizeException("Invalid or Expired Token");
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
    @Transactional
    @CachePut(value = "refreshToken",key = "'token'")
    public Token saveUser(Token token){
        return tokenRepository.save(token);
    }
}
