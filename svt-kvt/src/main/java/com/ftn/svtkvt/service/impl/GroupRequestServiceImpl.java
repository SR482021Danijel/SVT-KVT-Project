package com.ftn.svtkvt.service.impl;

import com.ftn.svtkvt.repository.GroupRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupRequestServiceImpl {

    @Autowired
    private GroupRequestRepository groupRequestRepository;

    public Boolean isInGroup(Long groupId, Long userId) {

        Boolean b = groupRequestRepository.isInGroup(groupId, userId);

        return b != null;
    }
}
