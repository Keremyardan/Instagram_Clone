package com.instagram.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    private Integer tagId;
    private Integer userId;
    private Integer postId;
    private String tagTimestamp;
}
