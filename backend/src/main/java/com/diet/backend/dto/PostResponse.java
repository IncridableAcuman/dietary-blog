package com.diet.backend.dto;

import com.diet.backend.enums.Category;

import java.io.Serializable;
import java.time.LocalDateTime;

public record PostResponse(
        String id,
        String title,
        String content,
        String authorId,
        String image,
        Category category,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) implements Serializable {
}
