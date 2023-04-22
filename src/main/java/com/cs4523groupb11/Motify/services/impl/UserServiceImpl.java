package com.cs4523groupb11.Motify.services.impl;

import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.repositories.UserRepository;
import com.cs4523groupb11.Motify.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findById(String userId) {
        return userRepository.findById(userId);
    }

    public List<User> findListByIds(List<String> idList){
        List<User> userList = userRepository.findAllByIdIn(idList);
        if (userList.size() != idList.size()){
            return Collections.emptyList();
        }
        return userList;
    }

    public List<User> findAllByUsername(String username){
        return userRepository.findAllByUsername(username);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

}
