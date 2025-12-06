package com.diet.backend.dto;

import com.diet.backend.enums.Category;
import com.diet.backend.enums.PostTags;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PostRequest {
    @NotBlank(message = "Title must be required")
    @Size(min = 5,max = 255,message = "Title must between 5 and 255 characters")
    private String title;

    @NotBlank(message = "Content is required")
    @Size(min = 15,max = 1024,message = "Content must between 15 and 1024 characters")
    private String content;

    @NotNull(message = "Image must be required")
    private MultipartFile image;

    @NotBlank(message = "Tags must be required")
    private List<PostTags> tags;

    @NotBlank(message = "Category must be required")
    private Category category;
}
