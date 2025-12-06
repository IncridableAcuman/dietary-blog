package com.diet.backend.controller;

import com.diet.backend.dto.UserResponse;
import com.diet.backend.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/users/role")
    public ResponseEntity<UserResponse> editRole(@RequestParam String id){
        return ResponseEntity.ok(adminService.editRole(id));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/user")
    public ResponseEntity<UserResponse> deleteUserById(@RequestParam String id){
        return ResponseEntity.ok(adminService.deleteUserById(id));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users")
    public ResponseEntity<String> deleteAllUsers(){
        adminService.deleteAllUsers();
        return ResponseEntity.ok("Users deleted successfully");
    }
}
