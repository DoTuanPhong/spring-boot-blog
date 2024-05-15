package com.supahiki.blog.repository;

import com.supahiki.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    /*User finByEmail(String email);
    List<User> findAllByEnable(boolean enabled);*/
}
