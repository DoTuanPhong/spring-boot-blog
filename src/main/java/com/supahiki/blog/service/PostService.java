package com.supahiki.blog.service;

import com.supahiki.blog.model.Post;
import com.supahiki.blog.model.User;
import com.supahiki.blog.repository.PostRepository;
import com.supahiki.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Post> getAllPosts (){
        return postRepository.findAll();
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


}
