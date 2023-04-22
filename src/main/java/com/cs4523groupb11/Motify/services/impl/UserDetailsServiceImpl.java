package com.cs4523groupb11.Motify.services.impl;

import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.security.UserDetailsImpl;
import com.cs4523groupb11.Motify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> opUser = userRepository.findByEmail(email);
        if (opUser.isEmpty()){
            throw new UsernameNotFoundException("User Not Found with email: " + email);
        }
        return UserDetailsImpl.build(opUser.get());
    }

}