package com.ftn.svtkvt.security;

import com.ftn.svtkvt.model.entity.Post;
import com.ftn.svtkvt.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WebSecurity {

    @Autowired
    private PostService postService;

    public boolean checkPostId(Long id) {
        Post post = postService.findById(id);
        return post != null;
    }
}
