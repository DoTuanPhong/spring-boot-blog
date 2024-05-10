package com.supahiki.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import com.supahiki.blog.model.Post;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@SpringBootApplication
//		(exclude = {
//		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
//		org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class}
//)
@RestController
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@GetMapping("")
	public List<Post> hello(){
		return List.of(new Post(1,"Sample Post Title", "Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
	}
}
