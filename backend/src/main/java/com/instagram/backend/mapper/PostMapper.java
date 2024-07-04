package com.instagram.backend.mapper;

import com.instagram.backend.model.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import java.util.List;

@Mapper
@Component
public interface PostMapper {
    @Insert("INSERT INTO posts(post_identifier, image_url, user_id, post_date, post_location," +
        "post_caption, post_alt, post_comments, post_likes, allow_comment, allow_like)" +
            "values(#{postIdentifier}, #{imageUrl}, #{userId}, #{postDate}, #{postLocation}, #{postCaption}," +
            "#{postAlt}, #{postComments}, #{postLikes}, #{allowComment}, #{allowLike})")
    int insertPost (String postIdentifier, String imageUrl, int userId, String postDate, String postLocation, String postCaption, String postAlt,
                    int postComments, int postLikes, boolean allowComment, boolean allowLike);

    @Select("SELECT * FROM post WHERE user_id = #{id}")
    List<Post> getPostById(int id);

    @Select("SELECT * FROM posts WHERE user_name = #{userName}")
    List<Post> getPostsByName (String userName);

    @Select("SELECT * FROM posts WHERE post_identifier = #{identifier}")
    List<Post> getPostByIdentifier (String identifier);
}
