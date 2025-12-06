package com.diet.backend.controller;

import com.diet.backend.dto.PostRequest;
import com.diet.backend.dto.PostResponse;
import com.diet.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@ModelAttribute PostRequest postRequest){
        return ResponseEntity.ok(postService.createPost(postRequest));
    }
}
