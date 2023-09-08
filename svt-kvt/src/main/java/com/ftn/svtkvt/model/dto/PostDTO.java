package com.ftn.svtkvt.model.dto;

import com.ftn.svtkvt.model.entity.EReactionType;
import com.ftn.svtkvt.model.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {

    private Long id;

    private String content;

    private LocalDateTime creationDate;

    private String username;

    private int heartCount;

    private int likeCount;

    private int dislikeCount;

    private EReactionType checked;

    public PostDTO(Post post, int heartCount, int likeCount, int dislikeCount, EReactionType checked) {
        this.id = post.getId();
        this.content = post.getContent();
        this.creationDate = post.getCreationDate();
        this.username = post.getPoster().getUsername();
        this.heartCount = heartCount;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.checked = checked;
    }
}
