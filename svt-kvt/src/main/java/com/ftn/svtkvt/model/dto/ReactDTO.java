package com.ftn.svtkvt.model.dto;

import com.ftn.svtkvt.model.entity.EReactionType;
import com.ftn.svtkvt.model.entity.Post;
import com.ftn.svtkvt.model.entity.Reaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReactDTO {

    private EReactionType reactionType;

    private LocalDateTime timeStamp;

    private Post post;

    private String username;

    public ReactDTO(Reaction reaction){
        this.reactionType = reaction.getType();
        this.timeStamp= reaction.getTimeStamp();
    }
}
