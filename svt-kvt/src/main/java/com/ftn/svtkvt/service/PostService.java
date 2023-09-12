package com.ftn.svtkvt.service;

import com.ftn.svtkvt.model.entity.Post;
import com.ftn.svtkvt.model.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface PostService {

    Post findByUser(String username);

    Post findById(Long id);

    List<Post> findAll();
    Post createPost(String content, LocalDateTime date, User user);

    void deletePost(Long id);
}
