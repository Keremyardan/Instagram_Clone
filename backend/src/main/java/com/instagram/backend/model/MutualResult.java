package com.instagram.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MutualResult {
    private Integer userId;
    private String userName;
    private String userAvatar;
    private Integer mutualNumber;
    private List<MutualFriend> mutual;
}
