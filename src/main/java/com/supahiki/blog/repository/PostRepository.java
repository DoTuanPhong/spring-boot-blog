package com.supahiki.blog.repository;
import com.supahiki.blog.model.Post;
import com.supahiki.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByAuthor(User author);
}
