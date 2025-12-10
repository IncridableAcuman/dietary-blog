package com.diet.backend.service;

import com.diet.backend.entity.User;
import com.diet.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OnlyUserService {
    private final UserRepository userRepository;

    @Transactional
    @CachePut(value = "users", key = "#result.id")
    public User saveUser(User user){
        return userRepository.save(user);
    }
}
