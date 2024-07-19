package com.instagram.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instagram.backend.mapper.ChatMapper;
import com.instagram.backend.model.Chat;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.logging.Logger;

@RestController
@CrossOrigin
public class ChatController {
    protected static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private ChatMapper chatMapper;

    @Autowired
    public Chat chat;

    @GetMapping("/chats/allchats/{sessionId}")
    public Object getChatsBySessionId(@PathVariable("sessionId") String sessionId) {
        return chatMapper.getChatsBySessionId(sessionId);
    }

    @GetMapping("/chats/sessionId/{sessionId}")
    public Object getLatestSession(@PathVariable("sessionId") String sessionId) {
        return chatMapper.getLatestTime(sessionId);
    }

    @PostMapping("/chats/new")
    public Integer newChat(@RequestBody String content) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        chat = objectMapper.readValue(content, Chat.class);
        chatMapper.createChat(chat.getSessionId(), chat.getUserId(), chat.getChatContent(), chat.getChatTimestamp());
        return 1;
    }
}
