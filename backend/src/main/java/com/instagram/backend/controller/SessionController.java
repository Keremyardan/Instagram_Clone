package com.instagram.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instagram.backend.mapper.ChatMapper;
import com.instagram.backend.mapper.SessionMapper;
import com.instagram.backend.model.Chat;
import com.instagram.backend.model.DetailedSession;
import com.instagram.backend.model.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class SessionController {

    protected static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private ChatMapper chatMapper;

    @Autowired
    private SessionMapper sessionMapper;

    @Autowired
    public Session session;

    @GetMapping("/session/userid/{userId}")
    public Object fetchSessionsByUserId(@PathVariable("userId") Integer userId) {
        List<DetailedSession> sessions = sessionMapper.fetchSessionsById(userId);
        for (DetailedSession detailedSession : sessions) {
            Chat c = chatMapper.getLatestTime(detailedSession.getSessionId());
            if (c != null) {
                detailedSession.setMessageDigestion(c.getChatContent());
                detailedSession.setUpdateTime(c.getChatTimestamp());
            }
        }
        return sessions;
    }

    @GetMapping("/sessions/sessionid/{sessionId}")
    public Object getSessionsBySessionId(@PathVariable("sessionId") String sessionId) {
        return sessionMapper.getSessionBySessionId(sessionId);
    }

    @PostMapping("/sessions/new")
    public Integer createSession(@RequestBody String content) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        session = objectMapper.readValue(content, Session.class);
        sessionMapper.createSession(session.getSessionId(), session.getUserAId(), session.getUserAName(), session.getUserAAvatar(),
                session.getUserBId(), session.getUserBName(), session.getUserBAvatar(), session.getSessionTimestamp()
        );
        return 1;
    }
}
