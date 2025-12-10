package com.diet.backend.service;

import com.diet.backend.dto.PostRequest;
import com.diet.backend.dto.PostResponse;
import com.diet.backend.entity.Post;
import com.diet.backend.entity.User;
import com.diet.backend.exception.BadRequestException;
import com.diet.backend.exception.NotFoundException;
import com.diet.backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

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
        post = savePost(post);
        return postResponse(post);
    }
    @Transactional
    @Cacheable(value = "posts", key = "'allPosts'")
    public List<PostResponse> postsList(){
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::postResponse).toList();
    }
    @Transactional
    public PostResponse getPost(String id){
        Post post = findPostById(id);
        return postResponse(post);
    }
    @Transactional
    @CacheEvict(value = "posts",key = "#id")
    public PostResponse remove(String id){
        Post post = findPostById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        User user = (User) authentication.getPrincipal();
        assert user != null;
        if (!post.getAuthorId().equals(user.getId())){
            throw new BadRequestException("Only author can delete this post");
        }
        postRepository.delete(post);
        return postResponse(post);
    }
    @Transactional
    public PostResponse editPost(String id,PostRequest request){
        Post post = findPostById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        User user = (User) authentication.getPrincipal();
        assert user != null;
        if (!post.getAuthorId().equals(user.getId())){
            throw new BadRequestException("Only author can delete this post");
        }
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCategory(request.getCategory());
        post.setTags(request.getTags());
        post.setImage(fileService.saveFile(request.getImage()));
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        post.setAuthorId(user.getId());
        post.setCommentIds(Collections.singletonList("A"));
        post = postRepository.save(post);
        return postResponse(post);
    }
    @Transactional
    @Cacheable(value = "posts",key = "#id")
    public Post findPostById(String id){
        return postRepository.findById(id).orElseThrow(()->new NotFoundException("Post not found"));
    }
    @CachePut(value = "posts",key = "#result.id")
    public Post savePost(Post post){
        return postRepository.save(post);
    }
}
