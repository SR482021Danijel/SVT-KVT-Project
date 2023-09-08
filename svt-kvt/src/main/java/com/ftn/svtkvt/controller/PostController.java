package com.ftn.svtkvt.controller;

import com.ftn.svtkvt.model.dto.PostDTO;
import com.ftn.svtkvt.model.dto.ReactDTO;
import com.ftn.svtkvt.model.entity.EReactionType;
import com.ftn.svtkvt.model.entity.Post;
import com.ftn.svtkvt.model.entity.Reaction;
import com.ftn.svtkvt.model.entity.User;
import com.ftn.svtkvt.service.PostService;
import com.ftn.svtkvt.service.UserService;
import com.ftn.svtkvt.service.impl.ReactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private ReactionServiceImpl reactionService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody PostDTO postDTO){

        User user = userService.findByUsername(postDTO.getUsername());

        Post created = postService.createPost(postDTO.getContent(), postDTO.getCreationDate(), user);

        if(created == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/getAll")
    public ResponseEntity getAll(@RequestBody PostDTO postDTOUsername){

        List<Post> posts = postService.findAll();
        User user = userService.findByUsername(postDTOUsername.getUsername());
        List<PostDTO> postDTOList = new ArrayList<>();
        for (Post post: posts) {
            int heartCount = reactionService.countAllHearts(post.getId());
            int likeCount = reactionService.countAllLikes(post.getId());
            int dislikeCount = reactionService.countAllDislikes(post.getId());
            EReactionType type = reactionService.findChecked(user.getId(), post.getId());
            PostDTO postDTO = new PostDTO(post, heartCount, likeCount, dislikeCount, type);
            postDTOList.add(postDTO);
        }

        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }

    @PutMapping("/changeReaction")
    public ResponseEntity changeReaction(@RequestBody ReactDTO reactDTO){

        User user = userService.findByUsername(reactDTO.getUsername());

        if (reactionService.findChecked(user.getId(), reactDTO.getPost().getId()) != null){
            reactionService.updateReaction(reactDTO.getReactionType().ordinal(), user.getId(), reactDTO.getPost().getId());
        } else {
            reactionService.createReaction(reactDTO);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deletePost(Long id){

        postService.deletePost(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
