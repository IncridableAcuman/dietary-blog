package com.diet.backend.controller;

import com.diet.backend.dto.UserRequest;
import com.diet.backend.dto.UserResponse;
import com.diet.backend.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PatchMapping("/edit")
    public ResponseEntity<UserResponse> editProfile(@RequestParam String id, @ModelAttribute UserRequest request){
        return ResponseEntity.ok(profileService.editProfile(id,request));
    }
}
