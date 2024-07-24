package com.instagram.backend.controller;

import jdk.jfr.Category;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index page";
    }
}
