package com.diet.backend.controller;

import com.diet.backend.dto.UserResponse;
import com.diet.backend.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PatchMapping("/users/role")
    public ResponseEntity<UserResponse> editRole(@RequestParam String id){
        return ResponseEntity.ok(adminService.editRole(id));
    }
}
