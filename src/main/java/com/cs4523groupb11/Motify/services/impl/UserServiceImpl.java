package com.cs4523groupb11.Motify.services.impl;

import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.entities.jpaprojections.UserIdProjection;
import com.cs4523groupb11.Motify.repositories.UserRepository;
import com.cs4523groupb11.Motify.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findById(String userId) {
        return userRepository.findById(userId);
    }

    public Optional<String> findByUsername(String username){
        Optional<UserIdProjection> opProj = userRepository.findByUsername(username);
        return opProj.map(UserIdProjection::getId);
    }

    public Optional<String> findByEmail(String email){
        Optional<UserIdProjection> opProj = userRepository.findByEmail(email);
        return opProj.map(UserIdProjection::getId);
    }

    public Optional<String> findByUsernameAndPassword(String username, String password){
        Optional<UserIdProjection> opProj = userRepository.findByUsernameAndPassword(username, password);
        return opProj.map(UserIdProjection::getId);
    }

    @Transactional
    public Optional<User> create(User user){
        if (userRepository.existsById(user.getId())){
            return Optional.empty();
        }
        return Optional.of(userRepository.save(user));
    }

    @Transactional
    public Optional<User> delete(String userId) {
        Optional<User> opUser = userRepository.findById(userId);
        if (opUser.isPresent()){
            userRepository.deleteById(userId);
            return opUser;
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<User> update(User user){
        User resUser;
        try{
            resUser = userRepository.saveAndFlush(user);
        }catch(DataIntegrityViolationException e){
            return Optional.empty();
        }
        return Optional.of(resUser);
    }
}
