package com.diet.backend.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String username;
    private MultipartFile avatar;
}
