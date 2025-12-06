package com.diet.backend.controller;

import com.diet.backend.dto.UserResponse;
import com.diet.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> userList(){
        return ResponseEntity.ok(userService.userList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getAUserById(@PathVariable String id){
        return ResponseEntity.ok(userService.getAUserById(id));
    }
}
