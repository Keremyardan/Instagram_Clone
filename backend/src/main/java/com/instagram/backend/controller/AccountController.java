package com.instagram.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instagram.backend.mapper.UserMapper;
import com.instagram.backend.model.Image;
import com.instagram.backend.model.User;
import com.instagram.backend.utils.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AccountController {
    protected static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    public User user;

    @Autowired
    public Image image;

    // generally for get methods, Pathvariable is being used
    @GetMapping("accounts/validate/username/{username}")
    public boolean checkUserName (@PathVariable("username") String username){
        List<String> allUserName = userMapper.getAllUserNames();
        return !allUserName.contains(username);
    }

    @GetMapping("accounts/validate/email/{email}")
    public boolean checkEmail(@PathVariable("email") String email) {
        List<String> allEmails = userMapper.getAllEmails();
        return !allEmails.contains(email);
    }

    @PostMapping("/accounts/signup")
    public String signup(@RequestBody String content) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        user = objectMapper.readValue(content, User.class);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        String token = JWTUtil.sign(user.getUserName(), user.getUserId());
        userMapper.insertUser(user.getUserName(), user.getEmail(), user.getPassword(),user.getFullName(), user.getAvatar());
        return token;
    }

    // generally for post and put methods, RequestBody is being used
    @PostMapping("accounts/avatar")
    public Integer avatar(@RequestBody String content) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        image = objectMapper.readValue(content, Image.class);
        Integer res = userMapper.updateAvatar(image.getUserName(), image.getImageUrl());
        return res;
    }

    @PostMapping("accounts/update")
    public Integer userUpdate(@RequestBody String content) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        user = objectMapper.readValue(content, User.class);
        Integer res = userMapper.updateInSettings(user.getUserName(), user.getAvatar(), user.getFullName(), user.getWebSite(), user.getBio(),user.getPhoneNumber());
        return res;

    }
}
