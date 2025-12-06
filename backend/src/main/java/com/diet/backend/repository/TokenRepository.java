package com.diet.backend.repository;

import com.diet.backend.entity.Token;
import com.diet.backend.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TokenRepository extends MongoRepository<Token, String> {
    Optional<Token> findByUser(User user);
}
