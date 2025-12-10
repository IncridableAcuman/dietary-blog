package com.diet.backend.dto;

import com.diet.backend.enums.Role;

import java.io.Serializable;

public record UserResponse(
         String id,
         String firstName,
         String lastName,
         String username,
         String email,
         Role role,
         String avatar
        ) implements Serializable {
}
