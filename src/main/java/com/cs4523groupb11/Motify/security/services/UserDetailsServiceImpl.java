package com.cs4523groupb11.Motify.security.services;

import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.entities.jpaprojections.UserIdProjection;
import com.cs4523groupb11.Motify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserIdProjection> opId = userRepository.findByUsername(username);
        if (opId.isEmpty()){
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        Optional<User> opUser = userRepository.findById(opId.get().getId());
        if (opUser.isEmpty()){
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        return UserDetailsImpl.build(opUser.get());
    }

}