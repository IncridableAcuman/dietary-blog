package com.diet.backend.dto;

import com.diet.backend.enums.Role;

import java.io.Serializable;

public record AuthResponse(
        String id,
        String firstName,
        String lastName,
        String username,
        String email,
        Role role,
        String avatar,
        String accessToken
) implements Serializable {
}
