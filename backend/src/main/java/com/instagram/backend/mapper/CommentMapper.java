package com.instagram.backend.mapper;

import com.instagram.backend.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface CommentMapper {

    @Insert("INSERT INTO comments(post_id, commented_id,comment_content,comment_timestamp, commenter_name, commenter_avatar) " +
            "values(#{postId}, #{commenterId}, #{commentContent}, #{commentTimestamp}, #{commenterName}, #{commenterAvatar})")
    int insertComment (Integer postId, Integer commenterId, String commentContent, String commentTimestamp, String commenterName, String commenterAvatar);

    @Select("SELECT * FROM comments WHERE post_id = #{postId}")
    List<Comment> fetchCommentsByPostId(Integer postId);
}
