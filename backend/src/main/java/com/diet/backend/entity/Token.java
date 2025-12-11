package com.diet.backend.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "tokens")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Token {
    @Id
    private String id;

    private String userId;

    private String refreshToken;

    private LocalDateTime expiryDate;

    public void setUser(String id) {
        this.userId=id;
    }
}
