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
    private UserMapper usermapper;

    @Autowired
    public User user;

    @Autowired
    public Image image;

    // generally for get methods, Pathvariable is being used
    @GetMapping("accounts/validate/username/{username}")
    public boolean checkUserName (@PathVariable("username") String username){
        List<String> allUserName = usermapper.getAllUserNames();
        return !allUserName.contains(username);
    }

    @GetMapping("accounts/validate/email/{email}")
    public boolean checkEmail(@PathVariable("email") String email) {
        List<String> allEmails = usermapper.getAllEmails();
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
        usermapper.insertUser(user.getUserName(), user.getEmail(), user.getPassword(),user.getFullName(), user.getAvatar());
        return token;
    }

    // generally for post and put methods, RequestBody is being used
    @PostMapping("accounts/avatar")
    public Integer avatar(@RequestBody String content) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        image = objectMapper.readValue(content, Image.class);
        Integer res = usermapper.updateAvatar(image.getUserName(), image.getImageUrl());
        return res;
    }

    @PostMapping("accounts/update")
    public Integer userUpdate(@RequestBody String content) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        user = objectMapper.readValue(content, User.class);
        Integer res = usermapper.updateInSettings(user.getUserName(), user.getAvatar(), user.getFullName(), user.getWebSite(), user.getBio(),user.getPhoneNumber());
        return res;
    }

    @PostMapping("accounts/login")
    public String login(@RequestBody String content) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        user = objectMapper.readValue(content, User.class);
        String emailOrPassword = user.getEmail();
        String lastLoginTime = user.getLastLogin();
        List<User> allUsers = usermapper.getAllUsers();
        List<String> allUserNames = usermapper.getAllUserNames();
        List<String> allEmails = usermapper.getAllEmails();
        String encodedPassword = "";
        String userName = "";
        Integer id = -1;
        logger.info(emailOrPassword);
        if(allUserNames.contains(emailOrPassword)) {
            for (User user : allUsers) {
                if(user.getUserName().equals(emailOrPassword)) {
                    encodedPassword = user.getPassword();
                    userName = user.getUserName();
                    id = user.getUserId();
                    break;
                }
            }
        } else if(allEmails.contains(emailOrPassword)) {
            for (User user : allUsers) {
                if(user.getEmail().equals(emailOrPassword)) {
                    encodedPassword = user.getPassword();
                    userName = user.getUserName();
                    id = user.getUserId();
                    break;
                }
            }
        } else return "NO_SUCH_ACCOUNT";
        logger.info(encodedPassword);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean result = passwordEncoder.matches(user.getPassword(), encodedPassword);
        if(!result) return "WRONG PASSWORD";
        usermapper.updateLoginTime(lastLoginTime,userName);
        String token = JWTUtil.sign(userName, id);
        return token;
    }
}
