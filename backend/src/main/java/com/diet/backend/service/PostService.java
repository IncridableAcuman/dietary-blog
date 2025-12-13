package com.diet.backend.service;

import com.diet.backend.dto.PostRequest;
import com.diet.backend.dto.PostResponse;
import com.diet.backend.entity.Post;
import com.diet.backend.entity.User;
import com.diet.backend.exception.BadRequestException;
import com.diet.backend.exception.NotFoundException;
import com.diet.backend.repository.PostRepository;
import com.diet.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final FileService fileService;
    private final UserRepository userRepository;

    public PostResponse postResponse(Post post){
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthorId(),
                post.getImage(),
                post.getCategory(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }

    @Transactional
    public PostResponse createPost(PostRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        assert userDetails != null;
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCategory(request.getCategory());
        post.setImage(fileService.saveFile(request.getImage()));
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        assert user != null;
        post.setAuthorId(user.getId());
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
    @CacheEvict(value = "posts",allEntries = true)
    public PostResponse remove(String id){
        Post post = findPostById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        assert userDetails != null;
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
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
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        assert userDetails != null;
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
        if (!post.getAuthorId().equals(user.getId())){
            throw new BadRequestException("Only author can edit this post");
        }
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCategory(request.getCategory());
        post.setImage(fileService.saveFile(request.getImage()));
        post.setUpdatedAt(LocalDateTime.now());
        post.setAuthorId(user.getId());
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
