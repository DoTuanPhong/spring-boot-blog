package com.supahiki.blog.service;

import com.supahiki.blog.dto.PostDto;
import com.supahiki.blog.model.Post;
import com.supahiki.blog.model.User;
import com.supahiki.blog.payload.PagedResponse;
import com.supahiki.blog.repository.PostRepository;
import com.supahiki.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public PagedResponse<PostDto> getAllPosts (
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "12") int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Post> postPage = postRepository.findAll(pageable);

        List<PostDto> content = postPage.getContent().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new PagedResponse<>(
                content,
                postPage.getNumber(),
                postPage.getSize(),
                postPage.getTotalElements(),
                postPage.getTotalPages(),
                postPage.isLast()
        );

    }

    public List<Post> getPostsByAuthor(String username){
        User author = userRepository.findByUsername(username);
        return postRepository.findByAuthor(author);
    }

    public Optional<Post> getPostById(Integer id){
        return postRepository.findById(id);
    }

    public Post createPost(Post post){
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    public Post updatePost(Integer id, Post postDetails){
        return postRepository.findById(id)
                .map(post -> {
                    post.setTitle(postDetails.getTitle());
                    post.setContent(postDetails.getContent());
                    post.setUpdatedAt(LocalDateTime.now());
                    return postRepository.save(post);
                }).orElseThrow(() -> new NoSuchElementException(("Post is not found with id " + id)));
    }

    public void deletePost(Integer id){
         Optional<Post> optionalPost = postRepository.findById(id);
         optionalPost.ifPresent(postRepository::delete);
    }

    private PostDto convertToDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getPostId());
        postDto.setTitle(post.getTitle());
        postDto.setAuthorName(post.getAuthor().getUsername());
        postDto.setCreatedAt(post.getCreatedAt());
        postDto.setUpdatedAt(post.getUpdatedAt());
        return postDto;
    }


}
