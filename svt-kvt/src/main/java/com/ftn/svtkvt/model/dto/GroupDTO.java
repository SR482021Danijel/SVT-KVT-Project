package com.ftn.svtkvt.model.dto;

import com.ftn.svtkvt.model.entity.Group;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class GroupDTO {

    String name;

    String description;

    LocalDateTime creationDate;

    String username;

    public GroupDTO(Group group){
        this.name = group.getName();
        this.description = group.getDescription();
        this.creationDate = group.getCreationDate();
    }
}
