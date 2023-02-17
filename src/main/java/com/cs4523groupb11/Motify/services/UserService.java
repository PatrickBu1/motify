package com.cs4523groupb11.Motify.services;


import com.cs4523groupb11.Motify.entities.User;

public interface UserService {
    public User findByUserId(String userId);
    public User findByUsername(String username);
    public User findByEmail(String email);
    public User findByUsernameAndPassword(String username, String password);
    public User createUser(User user);
    public User deleteUser(String userid);
    public User updateUser(User user);

}
