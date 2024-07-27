package com.instagram.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instagram.backend.mapper.TagMapper;
import com.instagram.backend.model.Tag;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import java.util.List;

@RestController
@CrossOrigin
public class TagController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    public Tag tag;

    @PostMapping("/tags/tag")
    public Integer tag(@RequestBody String content) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        tag = objectMapper.readValue(content, Tag.class);
        logger.info(tag.toString());
        tagMapper.insertTag(tag.getUserId(),tag.getPostId(),tag.getTagTimestamp());
        return 1;
    }

    @PostMapping("/tags/untag")
    public Integer unTag(@RequestBody String content) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        tag = objectMapper.readValue(content,Tag.class);
        tagMapper.deleteTag(tag.getUserId(),tag.getPostId());
        return 1;
    }

    @PostMapping("/tags/check")
    public boolean checkTag(@RequestBody String content) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        tag = objectMapper.readValue(content, Tag.class);
        List<Tag> res = tagMapper.checkIsTagging(tag.getUserId(),tag.getPostId());
        return res.size() != 0;
    }



}
