package com.ab.RedditClone.mapper;


import com.ab.RedditClone.dto.PostRequest;
import com.ab.RedditClone.dto.PostResponse;
import com.ab.RedditClone.model.Post;
import com.ab.RedditClone.model.Subreddit;
import com.ab.RedditClone.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    PostResponse mapToDto(Post post);

}
