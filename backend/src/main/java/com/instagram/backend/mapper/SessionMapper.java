package com.instagram.backend.mapper;

import com.instagram.backend.model.DetailedSession;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface SessionMapper {
    @Insert("INSERT INTO sessions (session_id, userA_id, userA_name, userA_avatar, userB_id, userB_avatar, session_timestamp)" +
    "values (#{sessionId}, #{userAId}, #{userAName}, #{userAAvatar}, #{userBId}, #{userBName}, #{userBAvatar}, #{sessionTimestamp})")
    int createSession (String sessionId, Integer userAId, String UserAName, String userAAvatar, Integer userBId, String userBName, String userBAvatar, String sessionTimestamp);

    @Select("SELECT * FROM sessions WGERE userA_id = #{id} OR userB_id = #{id}")
    List<DetailedSession> fetchSessionsById (Integer id);

    @Select("SELECT * FROM sessions WHERE session_id = #{sessionId}")
    List<DetailedSession> getSessionBySessionId (String sessionId);
}
