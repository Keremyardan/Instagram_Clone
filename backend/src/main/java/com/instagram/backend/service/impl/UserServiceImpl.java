package com.instagram.backend.service.impl;

import com.instagram.backend.mapper.UserMapper;
import com.instagram.backend.model.User;
import com.instagram.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }
}
