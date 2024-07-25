package com.instagram.backend.controller;

import com.instagram.backend.mapper.PostMapper;
import com.instagram.backend.mapper.UserMapper;
import com.instagram.backend.model.DetailedPost;
import com.instagram.backend.model.Post;
import com.instagram.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class PostController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    public User user;

    @Autowired
    public Post post;

    @Autowired
    public DetailedPost detailedPost;

    @GetMapping("/posts/user/{userId}")
    public Object getPostsById(@PathVariable("userId") Integer userId) {
        List<Post> allPosts = postMapper.getPostById(userId);
        return allPosts;
    }

}
