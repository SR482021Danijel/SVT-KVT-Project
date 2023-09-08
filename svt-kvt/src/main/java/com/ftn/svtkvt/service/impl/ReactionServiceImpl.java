package com.ftn.svtkvt.service.impl;

import com.ftn.svtkvt.model.dto.ReactDTO;
import com.ftn.svtkvt.model.entity.EReactionType;
import com.ftn.svtkvt.model.entity.Post;
import com.ftn.svtkvt.model.entity.Reaction;
import com.ftn.svtkvt.model.entity.User;
import com.ftn.svtkvt.repository.ReactionRepository;
import com.ftn.svtkvt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReactionServiceImpl {

    @Autowired
    private ReactionRepository reactionRepository;

    @Autowired
    private UserRepository userRepository;

    public Reaction createReaction(ReactDTO reactDTO){

        Reaction reaction = new Reaction();
        reaction.setType(reactDTO.getReactionType());
        reaction.setTimeStamp(reactDTO.getTimeStamp());
        Optional<User> user = userRepository.findFirstByUsername(reactDTO.getUsername());
        reaction.setPoster(user.get());
        reaction.getPosts().add(reactDTO.getPost());
        reaction = reactionRepository.save(reaction);

        return reaction;
    }

    public int countAllHearts(Long id){
        return reactionRepository.countAllHearts(id);
    }

    public int countAllLikes(Long id){
        return reactionRepository.countAllLikes(id);
    }

    public int countAllDislikes(Long id){
        return reactionRepository.countAllDislikes(id);
    }

    public EReactionType findChecked(Long userId, Long postId){
        return reactionRepository.findChecked(userId, postId);
    }

    public void updateReaction(int reactionType, Long user_id, Long post_id){
        reactionRepository.updateReaction(reactionType, user_id, post_id);
    }
}
