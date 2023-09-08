package com.ftn.svtkvt.controller;

import com.ftn.svtkvt.model.dto.GroupDTO;
import com.ftn.svtkvt.model.entity.Group;
import com.ftn.svtkvt.model.entity.User;
import com.ftn.svtkvt.service.UserService;
import com.ftn.svtkvt.service.impl.GroupRequestServiceImpl;
import com.ftn.svtkvt.service.impl.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/groups")
public class GroupController {

    @Autowired
    public GroupServiceImpl groupService;

    @Autowired
    public UserService userService;

    @Autowired
    public GroupRequestServiceImpl groupRequestService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody GroupDTO groupDTO){

        groupService.createGroup(groupDTO);

        return new ResponseEntity<>(groupDTO, HttpStatus.CREATED);
    }

    @PostMapping("/getAll")
    public ResponseEntity getAll(@RequestBody User userDto){

        List<Group> groupList = groupService.getAll();
        User user = userService.findByUsername(userDto.getUsername());

        List<GroupDTO> groupDTOList = new ArrayList<>();
        for (Group group: groupList){
            GroupDTO groupDTO = new GroupDTO(group);
            if (groupRequestService.isInGroup(group.getId(), user.getId())){
                groupDTO.setUsername(user.getUsername());
            }
            groupDTOList.add(groupDTO);
        }

        return new ResponseEntity<>(groupDTOList, HttpStatus.OK);
    }
}
