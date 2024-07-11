package com.instagram.backend.controller;

import com.instagram.backend.mapper.UserMapper;
import com.instagram.backend.model.Image;
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
public class AccountController {
    protected static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    public User user;

    @Autowired
    public Image image;

    @GetMapping("accounts/validate/email/{email}")
    public boolean checkEmail(@PathVariable("email") String email) {
        List<String> allEmails = userMapper.getAllEmails();
        return !allEmails.contains(email);
    }
}
