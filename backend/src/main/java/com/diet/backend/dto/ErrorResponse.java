package com.diet.backend.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
        String message,
        int status,
        String error,
        String path,
        LocalDateTime errorTime
) {
}
