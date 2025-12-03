package com.diet.backend.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "tokens")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Token {
    @Id
    private ObjectId id;

    private User user;

    private String refreshToken;

    private LocalDateTime expiryDate;
}
