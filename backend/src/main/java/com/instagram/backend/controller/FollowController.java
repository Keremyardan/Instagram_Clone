package com.instagram.backend.controller;

import com.instagram.backend.mapper.FollowMapper;
import com.instagram.backend.mapper.UserMapper;
import com.instagram.backend.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Component
public class FollowController {

    protected static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FollowMapper followMapper;

    @Autowired
    public User user;

    @Autowired
    public Follow follow;

    @GetMapping("/follows/followers/{userId}")
    public Object getFollowersById(@PathVariable("userId") Integer userId) {
        List<Follow> allFollowers = followMapper.getAllFollowers(userId);
        return allFollowers;
    }

    @GetMapping("/follows/recent")
    public Object getRecentFollows(@RequestParam("userId") Integer userId, @RequestParam("limit") Integer limit){
        return followMapper.getAllRecentFollows(userId, limit);
    }

    @GetMapping("/follows/mutual/{userId}")
    public Object getMutualFollowsByUserId(@PathVariable("userId") Integer userId) {
        List<User> queryusers = userMapper.getQueryObjects(userId);
        List<Follow> allFollowees = followMapper.getAllFollowees(userId);
        List<Integer> friends = new ArrayList<>();
        for (Follow f : allFollowees) {
            friends.add(f.getFollowedId());
        }
        List<MutualResult> res = new ArrayList<>();

    }
}
