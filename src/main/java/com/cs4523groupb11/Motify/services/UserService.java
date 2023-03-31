package com.cs4523groupb11.Motify.services;


import com.cs4523groupb11.Motify.entities.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(String userId);
    Optional<String> findByUsername(String username);
    Optional<String> findByEmail(String email);
    Optional<String> findByUsernameAndPassword(String username, String password);
    Optional<User> create(User user);
    Optional<User> delete(String userid);
    Optional<User> update(User user);

}
