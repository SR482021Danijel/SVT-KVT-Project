package com.ftn.svtkvt.service.impl;

import com.ftn.svtkvt.model.dto.GroupDTO;
import com.ftn.svtkvt.model.entity.Group;
import com.ftn.svtkvt.model.entity.GroupAdmin;
import com.ftn.svtkvt.model.entity.GroupRequest;
import com.ftn.svtkvt.model.entity.User;
import com.ftn.svtkvt.repository.GroupAdminRepository;
import com.ftn.svtkvt.repository.GroupRepository;
import com.ftn.svtkvt.repository.GroupRequestRepository;
import com.ftn.svtkvt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupAdminRepository groupAdminRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRequestRepository groupRequestRepository;

    public Group createGroup(GroupDTO groupDTO){

        Group group = new Group();
        group.setName(groupDTO.getName());
        group.setDescription(groupDTO.getDescription());
        group.setCreationDate(groupDTO.getCreationDate());

        group = groupRepository.save(group);

        Optional<User> user = userRepository.findFirstByUsername(groupDTO.getUsername());

        GroupAdmin groupAdmin = new GroupAdmin();
        groupAdmin.setGroup(group);
        groupAdmin.setUser(user.get());

        groupAdminRepository.save(groupAdmin);

        GroupRequest groupRequest = new GroupRequest();
        groupRequest.setApproved(true);
        groupRequest.setGroup(group);
        groupRequest.setCreated_at(groupDTO.getCreationDate());
        groupRequest.setUser(user.get());

        groupRequestRepository.save(groupRequest);

        return group;
    }

    public List<Group> getAll(){
        return groupRepository.findAll();
    }
}
