package com.diet.backend.dto;

import com.diet.backend.enums.Role;
import org.bson.types.ObjectId;

public record AuthResponse(
        ObjectId id,
        String firstName,
        String lastName,
        String username,
        String email,
        Role role,
        String avatar,
        String accessToken
) {
}
