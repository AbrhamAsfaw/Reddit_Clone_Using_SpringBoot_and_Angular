package com.ab.RedditClone.repository;

import com.ab.RedditClone.model.Post;
import com.ab.RedditClone.model.Subreddit;
import com.ab.RedditClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}
