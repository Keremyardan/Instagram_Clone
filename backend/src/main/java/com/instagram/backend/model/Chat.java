package com.instagram.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    Integer chatId;
    String sessionId;
    Integer userId;
    String chatContent;
    String chatTimestamp;
}
