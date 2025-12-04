package com.diet.backend.dto;

import com.diet.backend.enums.Role;

public record UserResponse(
         String id,
         String firstName,
         String lastName,
         String username,
         String email,
         Role role,
         String avatar
        ) {
}
