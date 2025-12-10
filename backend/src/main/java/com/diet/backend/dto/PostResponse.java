package com.diet.backend.dto;

import com.diet.backend.enums.Category;
import com.diet.backend.enums.PostTags;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public record PostResponse(
        String id,
        String title,
        String content,
        String authorId,
        String image,
        List<PostTags>tags,
        Category category,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<String> commentIds
) implements Serializable {
}
