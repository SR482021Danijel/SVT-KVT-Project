package com.ftn.svtkvt.service.impl;

import com.ftn.svtkvt.model.dto.CommentDTO;
import com.ftn.svtkvt.model.entity.Comment;
import com.ftn.svtkvt.model.entity.Post;
import com.ftn.svtkvt.model.entity.User;
import com.ftn.svtkvt.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl {

    @Autowired
    private CommentRepository commentRepository;

    public Comment add(CommentDTO commentDTO, User user, Post post) {

        Comment comment = new Comment();
        comment.setText(commentDTO.getText());
        comment.setTimeStamp(commentDTO.getTimeStamp());
        comment.setUser(user);
        comment.setPost(post);

        return commentRepository.save(comment);
    }

    public List<Comment> findAllByPost(Long postId) {

        return commentRepository.findAllByPostId(postId);

    }
}
