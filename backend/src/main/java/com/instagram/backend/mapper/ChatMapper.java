package com.instagram.backend.mapper;

import com.instagram.backend.model.Chat;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface ChatMapper {
    @Insert("INSERT INTO chats(session_id, user_id, chat_content, chat_timestamp)" +
            " values(#{sessionId}, #{userId}, #{chatContent},#{chatTimestamp})")
    int createChat(String sessionId, Integer userId,String chatContent, String chatTimestamp);

    @Select("SELECT * FROM chats WHERE session_id = #{sessionId} ORDER BY chat_timestamp DESC")
    List<Chat> getChatsBySessionId(String sessionId);

    @Select("SELECT chat_timestamp,chat_content FROM chats WHERE session_id = #{sessionId} ORDER BY chat_timestamp DESC LIMIT 1")
    Chat getLatestTime(String sessionId);
}
