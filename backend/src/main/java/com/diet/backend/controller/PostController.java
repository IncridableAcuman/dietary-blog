package com.diet.backend.controller;

import com.diet.backend.dto.PostRequest;
import com.diet.backend.dto.PostResponse;
import com.diet.backend.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@ModelAttribute PostRequest postRequest){
        return ResponseEntity.ok(postService.createPost(postRequest));
    }
    @GetMapping
    public ResponseEntity<List<PostResponse>> postsList(){
        return ResponseEntity.ok(postService.postsList());
    }
    @GetMapping("/${id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable String id){
        return ResponseEntity.ok(postService.getPost(id));
    }
    @DeleteMapping("/${id}")
    public ResponseEntity<PostResponse> remove(@PathVariable String id){
        return ResponseEntity.ok(postService.remove(id));
    }
    @PatchMapping("/${id}")
    public ResponseEntity<PostResponse> editPost(@PathVariable String id,@Valid @RequestBody PostRequest request){
        return ResponseEntity.ok(postService.editPost(id,request));
    }
}
