package com.diet.backend.service;

import com.diet.backend.entity.Token;
import com.diet.backend.entity.User;
import com.diet.backend.exception.NotFoundException;
import com.diet.backend.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

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
}
