package com.ftn.svtkvt.service.impl;

import com.ftn.svtkvt.model.dto.UserDTO;
import com.ftn.svtkvt.model.entity.User;
import com.ftn.svtkvt.repository.UserRepository;
import com.ftn.svtkvt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {
        Optional<User> user = userRepository.findFirstByUsername(username);
        if (!user.isEmpty()) {
            return user.get();
        }
        return null;
    }

    @Override
    public User createUser(User createdUser) {

        Optional<User> user = userRepository.findFirstByUsername(createdUser.getUsername());

        if(user.isPresent()){
            return null;
        }

        User newUser = new User();
        newUser.setUsername(createdUser.getUsername());
        newUser.setPassword(passwordEncoder.encode(createdUser.getPassword()));
        newUser.setEmail(createdUser.getEmail());
        newUser.setFirstName(createdUser.getFirstName());
        newUser.setLastName(createdUser.getLastName());
        newUser = userRepository.save(newUser);

        return newUser;
    }

    @Override
    public void updateUser(User user){

    }
}
