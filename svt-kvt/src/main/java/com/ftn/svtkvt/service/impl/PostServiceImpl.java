package com.ftn.svtkvt.service.impl;

import com.ftn.svtkvt.model.entity.Post;
import com.ftn.svtkvt.model.entity.User;
import com.ftn.svtkvt.repository.PostRepository;
import com.ftn.svtkvt.repository.UserRepository;
import com.ftn.svtkvt.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Post findByUser(String username) {

        Optional<User> user = userRepository.findFirstByUsername(username);

        if (user.isPresent()) {
            Optional<Post> post = postRepository.findFirstByPoster_Id(user.get().getId());
            if (post.isPresent()) {
                return post.get();
            }
        }
        return null;
    }

    @Override
    public Post findById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.orElse(null);
    }

    @Override
    public List<Post> findAll() {

        return postRepository.findAll();

    }

    @Override
    public Post createPost(String content, LocalDateTime date, User user) {

        Post post = new Post();
        post.setContent(content);
        post.setCreationDate(date);
        post.setPoster(user);
        post = postRepository.save(post);

        return post;
    }

    @Override
    public void deletePost(Long id){
        postRepository.deleteById(id);
    }


}
