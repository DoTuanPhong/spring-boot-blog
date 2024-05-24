package com.supahiki.blog.controller;

import com.supahiki.blog.dto.PostDto;
import com.supahiki.blog.model.Post;
import com.supahiki.blog.payload.PagedResponse;
import com.supahiki.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostService postService;

    @GetMapping(value = {"", "/"})
    public PagedResponse<PostDto> getAllPosts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "12") int size){

        return postService.getAllPosts(page, size);
    }

    @GetMapping("/author/{username}")
    public List<Post> getPostsByAuthor(@PathVariable String username){
        return postService.getPostsByAuthor(username);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer id){
        Optional<Post> optionalPost = postService.getPostById(id);
        return optionalPost.map(post -> ResponseEntity.ok(modelMapper.map(post, PostDto.class)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Post CreatePost(@RequestBody Post postDetails){
        return postService.createPost(postDetails);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePostById(@PathVariable Integer id, @RequestBody Post postDetails){

        try{
            Post updatePost = postService.updatePost(id, postDetails);
            return ResponseEntity.ok(updatePost);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Integer id){
        postService.deletePost(id);
    }


}
