package com.diet.backend.service;

import com.diet.backend.dto.UserRequest;
import com.diet.backend.dto.UserResponse;
import com.diet.backend.entity.User;
import com.diet.backend.exception.BadRequestException;
import com.diet.backend.exception.NotFoundException;
import com.diet.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final FileService fileService;
    private final UserRepository userRepository;
    @Transactional
    public UserResponse editProfile(String id,UserRequest request){
        User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException("User not found"));
        if (!user.getId().equals(id)){
            throw new BadRequestException("Only author can edit this profile");
        }
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setAvatar(fileService.saveFile(request.getAvatar()));
        userRepository.save(user);
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
