package com.ftn.svtkvt.service;

import com.ftn.svtkvt.model.dto.UserDTO;
import com.ftn.svtkvt.model.entity.User;

public interface UserService {

    User findByUsername(String username);

    User createUser(User user);

    void updateUser(User user);
}
