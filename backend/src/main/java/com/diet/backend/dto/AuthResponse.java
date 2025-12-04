package com.diet.backend.dto;

import com.diet.backend.enums.Role;

public record AuthResponse(
        String id,
        String firstName,
        String lastName,
        String username,
        String email,
        Role role,
        String avatar,
        String accessToken
) {
}
