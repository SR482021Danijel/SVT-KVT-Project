package com.ftn.svtkvt.model.dto;

import com.ftn.svtkvt.model.entity.Comment;
import com.ftn.svtkvt.model.entity.EReactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {

    private Long id;
    private String text;
    private LocalDate timeStamp;
    private String username;
    private Long postId;
    private int heartCount;
    private int likeCount;
    private int dislikeCount;
    private EReactionType checked;

    public CommentDTO(Comment comment, int heartCount, int likeCount, int dislikeCount, EReactionType checked){
        this.id = comment.getId();
        this.text = comment.getText();
        this.timeStamp = comment.getTimeStamp();
        this.username = comment.getUser().getUsername();
        this.heartCount = heartCount;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.checked = checked;
    }
}
