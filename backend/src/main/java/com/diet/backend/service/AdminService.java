package com.diet.backend.service;

import com.diet.backend.dto.UserResponse;
import com.diet.backend.entity.User;
import com.diet.backend.enums.Role;
import com.diet.backend.exception.BadRequestException;
import com.diet.backend.exception.NotFoundException;
import com.diet.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse editRole(String id){
        User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException("User not found"));
        if (user.getRole().equals(Role.USER)){
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }
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
    @CacheEvict(value = "users",key = "#id")
    public UserResponse deleteUserById(String id){
        if (id == null){
            throw new BadRequestException("Invalid id");
        }
        User user = userRepository.findById(id).orElseThrow(()->new NotFoundException("User not found"));
        userRepository.delete(user);
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
    @CacheEvict(value = "users", key = "allUsers")
    public void deleteAllUsers(){
        List<User> users = userRepository.findAll();
        userRepository.deleteAll(users);
    }
}
