package com.diet.backend.controller;

import com.diet.backend.dto.UserResponse;
import com.diet.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> userList(){
        return ResponseEntity.ok(userService.userList());
    }
    @GetMapping
    public ResponseEntity<UserResponse> getAUserById(@RequestParam String id){
        return ResponseEntity.ok(userService.getAUserById(id));
    }
    @DeleteMapping
    public ResponseEntity<UserResponse> deleteUserById(@RequestParam String id){
        return ResponseEntity.ok(userService.deleteUserById(id));
    }
    @DeleteMapping
    public ResponseEntity<String> deleteAllUsers(){
        userService.deleteAllUsers();
        return ResponseEntity.ok("Users deleted successfully");
    }
}
