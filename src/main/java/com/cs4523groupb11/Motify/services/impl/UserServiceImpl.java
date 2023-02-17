package com.cs4523groupb11.Motify.services.impl;

import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    public User findByUserId(String userId){
        return new User();
    }

    public User findByUsername(String username){
        return new User("", "", "");
    }

    public User findByEmail(String email){
        return new User("", "", "");
    }
    public User findByUsernameAndPassword(String username, String password){
        return new User("", "", "");
    }
    public User createUser(User user){
        return new User("", "", "");
    }
    public User deleteUser(String userid){
        return new User("", "", "");
    }
    public User updateUser(User user){
        return new User("", "", "");
    }
}
