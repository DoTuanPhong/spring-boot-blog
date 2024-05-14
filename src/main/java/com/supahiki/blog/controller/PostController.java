package com.supahiki.blog.controller;

import com.supahiki.blog.model.Post;
import com.supahiki.blog.service.PostService;
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
    private PostService postService;

    @GetMapping
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/author/{username}")
    public List<Post> getPostsByAuthor(@PathVariable String username){
        return postService.getPostsByAuthor(username);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer id){
        Optional<Post> optionalPost = postService.getPostById(id);
        return optionalPost.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
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
