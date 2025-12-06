package com.diet.backend.entity;

import com.diet.backend.enums.Category;
import com.diet.backend.enums.PostTags;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "posts")
public class Post {
    @Id
   private String id;
   private String title;
   private String content;
   private String authorId;
   private String image;
   private List<PostTags> tags;
   private Category category;
   private LocalDateTime createdAt;
   private LocalDateTime updatedAt;
   private List<String> commentIds;
}
