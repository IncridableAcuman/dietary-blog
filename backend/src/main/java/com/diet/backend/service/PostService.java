package com.diet.backend.service;

import com.diet.backend.dto.PostRequest;
import com.diet.backend.dto.PostResponse;
import com.diet.backend.entity.Post;
import com.diet.backend.entity.User;
import com.diet.backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final FileService fileService;

    public PostResponse postResponse(Post post){
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthorId(),
                post.getImage(),
                post.getTags(),
                post.getCategory(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getCommentIds()
        );
    }

    @Transactional
    public PostResponse createPost(PostRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        User user = (User) authentication.getPrincipal();
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCategory(request.getCategory());
        post.setTags(request.getTags());
        post.setImage(fileService.saveFile(request.getImage()));
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        assert user != null;
        post.setAuthorId(user.getId());
        post.setCommentIds(Collections.singletonList("A"));
        postRepository.save(post);
        return postResponse(post);
    }
}
