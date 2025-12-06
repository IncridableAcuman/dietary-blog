package com.diet.backend.service;

import com.diet.backend.dto.UserResponse;
import com.diet.backend.entity.User;
import com.diet.backend.exception.BadRequestException;
import com.diet.backend.exception.NotFoundException;
import com.diet.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public List<UserResponse> userList(){
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getAvatar()
        )).toList();
    }
    @Transactional
    public UserResponse getAUserById(String id){
        if (id == null){
            throw new BadRequestException("Invalid id");
        }
        User user = userRepository.findById(id).orElseThrow(()->new NotFoundException("User not found"));
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

}
