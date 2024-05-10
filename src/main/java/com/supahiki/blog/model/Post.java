package com.supahiki.blog.model;

import java.util.Date;

public class Post {
    private int postId;
    private String title;
    private String content;
    private Date createdAt;
    private Date updatedAt;

    public Post() {
    }

    public Post(int postId, String title, String content) {
        this.postId = postId;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
