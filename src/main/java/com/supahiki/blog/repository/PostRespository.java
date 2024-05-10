package com.supahiki.blog.repository;
import com.supahiki.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRespository extends JpaRepository<Post, Long> {
}
