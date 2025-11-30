package com.diet.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "FirstName must be required")
    @Size(min = 3,max = 100,message = "FirstName must between 3 and 100 characters")
    private String firstName;

    @NotBlank(message = "LastName must be required")
    @Size(min = 3,max = 100,message = "LastName must between 3 and 100 characters")
    private String lastName;

    @NotBlank(message = "Username must be required")
    @Size(min = 3,max = 100,message = "FirstName must between 3 and 100 characters")
    private String username;

    @NotBlank(message = "Email must be required")
    @Email
    private String email;

    @NotBlank(message = "Password must be required")
    @Size(min = 8,max = 255,message = "FirstName must between 8 and 255 characters")
    private String password;
}
