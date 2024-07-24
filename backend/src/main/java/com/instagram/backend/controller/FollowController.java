package com.instagram.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instagram.backend.mapper.FollowMapper;
import com.instagram.backend.mapper.UserMapper;
import com.instagram.backend.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
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
    public Object getRecentFollows(@RequestParam("userId") Integer userId, @RequestParam("limit") Integer limit) {
        return followMapper.getAllRecentFollows(userId, limit);
    }

    @GetMapping("/follows/mutual/{userId}")
    public Object getMutualFollowsByUserId(@PathVariable("userId") Integer userId) {
        List<User> queryUsers = userMapper.getQueryObjects(userId);
        List<Follow> allFollowees = followMapper.getAllFollowees(userId);
        List<Integer> friends = new ArrayList<>();
        for (Follow f : allFollowees) {
            friends.add(f.getFollowedId());
        }
        List<MutualResult> res = new ArrayList<>();
        for (int i = 0; i < queryUsers.size(); i++) {
            List<MutualFriend> temp = userMapper.getMutualFriends(userId, queryUsers.get(i).getUserId());
            if (temp.size() != 0 && !friends.contains(queryUsers.get(i).getUserId())) {
                MutualResult mutualResult = new MutualResult();
                mutualResult.setUserId(queryUsers.get(i).getUserId());
                mutualResult.setUserAvatar(queryUsers.get(i).getAvatar());
                mutualResult.setUserName(queryUsers.get(i).getUserName());
                mutualResult.setMutualNumber(temp.size());
                res.add(mutualResult);
            }
        }
        return res;
    }

    @GetMapping("/follows/follow")
    public Integer follow(@RequestBody String content) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        follow = objectMapper.readValue(content, Follow.class);
        followMapper.inserfFollow(follow.getFollowerId(), follow.getFollowedId(), follow.getFollowTimestamp());
        return 1;
    }

    @GetMapping("/follows/unfollow")
    public Integer unfollow(@RequestBody String content) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        follow = objectMapper.readValue(content, Follow.class);
        followMapper.deleteFollow(follow.getFollowerId(), follow.getFollowedId());
        return 1;
    }

    @PostMapping("/follows/check")
    public boolean checkIsFollowing(@RequestBody String content) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        follow = objectMapper.readValue(content,Follow.class);
        logger.info(follow.toString());
        List<Follow> res = followMapper.checkIsFollowing(follow.getFollowId(), follow.getFollowedId());
        return res.size() == 1;
    }
}
