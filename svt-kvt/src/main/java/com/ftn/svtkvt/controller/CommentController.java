package com.ftn.svtkvt.controller;

import com.ftn.svtkvt.model.dto.CommentDTO;
import com.ftn.svtkvt.model.dto.ReactDTO;
import com.ftn.svtkvt.model.entity.Comment;
import com.ftn.svtkvt.model.entity.EReactionType;
import com.ftn.svtkvt.model.entity.Post;
import com.ftn.svtkvt.model.entity.User;
import com.ftn.svtkvt.service.PostService;
import com.ftn.svtkvt.service.UserService;
import com.ftn.svtkvt.service.impl.CommentServiceImpl;
import com.ftn.svtkvt.service.impl.ReactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/comments")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private ReactionServiceImpl reactionService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody CommentDTO commentDTO){

        User user = userService.findByUsername(commentDTO.getUsername());
        Post post = postService.findById(commentDTO.getPostId());

        commentService.add(commentDTO, user, post);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity getAllByPost(@PathVariable Long id) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.findByUsername(userDetails.getUsername());

        List<CommentDTO> commentDTOList = new ArrayList<>();
        List<Comment> comments = commentService.findAllByPost(id);
        for (Comment comment: comments){
            int heartCount = reactionService.countAllComHearts(comment.getId());
            int likeCount = reactionService.countAllComLikes(comment.getId());
            int dislikeCount = reactionService.countAllComDislikes(comment.getId());
            EReactionType reactionType = reactionService.findCheckedComment(user.getId(), comment.getId());
            CommentDTO commentDTO = new CommentDTO(comment, heartCount, likeCount, dislikeCount, reactionType);
            commentDTOList.add(commentDTO);
        }

        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }

    @PutMapping("/changeCommentReaction")
    public ResponseEntity changeCommentReaction(@RequestBody ReactDTO reactDTO){

        User user = userService.findByUsername(reactDTO.getUsername());

        if (reactionService.findCheckedComment(user.getId(), reactDTO.getComment().getId()) != null) {
            reactionService.updateCommentReaction(reactDTO.getReactionType().ordinal(), user.getId(), reactDTO.getComment().getId());
        } else {
            reactionService.createCommentReaction(reactDTO);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
