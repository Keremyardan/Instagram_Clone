package com.instagram.backend.mapper;

import com.instagram.backend.model.DetailedFollow;
import com.instagram.backend.model.Follow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface FollowMapper {
    @Insert("INSERT INTO follows(follower_id, followee_id, follow_timestamp values (#{followeeId}, {followTimestamo})")
    int insertFollow(Integer followerId, Integer followeeId, String followTimestamp);

    @Delete("DELETE FROM follows WHERE folloer_id = #{followerId} and followee_id = #{followeeId}")
    int deleteFollow(Integer followerId, Integer followeeId);

    @Select("SELECT * FROM follows WHERE followee_id = #{userId}")
    List<Follow> getAllFollowers(Integer userId);

    @Select("SELECT * FROM follows WHERE follower_id = #{id}")
    List<Follow> getAllFollowees(Integer id);

    @Select("SELECT * FROM follows WHERE follower_id = #{a} and followee_id = #{b}")
    List<Follow> checkIsFollowing(Integer a, Integer b);

    @Select("SELECT user_id, user_name, avatar, follow_timestamp \n" +
    "FROM follows, users \n" +
    "WHERE follows.followee_id = #{userId} AND follows.follower_id = user.user_id \n" +
    "ORDER BY follow_timestamp DESC \n" +
    "LIMIT #{limit}")
    List<DetailedFollow> getAllRecentFollows(Integer userId, Integer limit);
}
