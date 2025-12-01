package com.diet.backend.service;

import com.diet.backend.entity.Token;
import com.diet.backend.entity.User;
import com.diet.backend.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    public Token create(User user,String refreshToken){
        Token token =new Token();
        token.setUser(user);
        token.setRefreshToken(refreshToken);
        token.setExpiryDate(LocalDateTime.now().plusDays(7));
        return tokenRepository.save(token);
    }

}
