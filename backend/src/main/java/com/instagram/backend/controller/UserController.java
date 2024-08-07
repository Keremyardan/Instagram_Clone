package com.instagram.backend.controller;

import com.instagram.backend.mapper.UserMapper;
import com.instagram.backend.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    public User user;

    @GetMapping("/users/username/{username}")
    public Object getUserByName(@PathVariable("username") String username) {
        return userMapper.getUserByName(username);
    }

    @GetMapping("/users/users/{userId}")
    public Object getAllUsers(@PathVariable("userId") Integer UserId){
        List<User> allUsers = userMapper.getAllUsers();
        return allUsers;
    }

    @GetMapping("/users/userid/{userId}")
    public Object getUserById(@PathVariable("userId") Integer userId) {
        return userMapper.getUserById(userId);
    }

    @GetMapping("/users/query/{query}")
    public Object queryUser(@PathVariable("query") Integer query) {
        return userMapper.queryUsers("%" + query + "%");
    }

    @GetMapping("/users/random/{num}")
    public Object getRandomUsers(@PathVariable("num") Integer num) {
        return userMapper.getRandomUsers(num);
    }

    @GetMapping("/users/login/{userId}")
    public Object getLoginTime(@PathVariable("userId") Integer userId) {
        return userMapper.getLoginTime(userId);
    }
}
