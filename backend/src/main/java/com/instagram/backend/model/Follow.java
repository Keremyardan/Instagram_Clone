package com.instagram.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Follow {
    private Integer followId;
    private Integer followerId;
    private Integer followedId;
    private String followTimestamp;
}
