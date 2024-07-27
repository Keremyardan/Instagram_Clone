package com.instagram.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    private String sessionId;
    private Integer userAId;
    private String userAName;
    private String userAAvatar;
    private Integer userBId;
    private String userBName;
    private String userBAvatar;
    private String sessionTimestamp;
}
