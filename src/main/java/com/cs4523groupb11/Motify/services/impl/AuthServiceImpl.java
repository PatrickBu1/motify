package com.cs4523groupb11.Motify.services.impl;

import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.services.AuthService;
import com.cs4523groupb11.Motify.services.UserService;
import com.cs4523groupb11.Motify.utils.JwtTokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private JwtTokenUtility utility;
    private UserService userService;

    @Autowired
    public AuthServiceImpl (JwtTokenUtility utility, UserService userService){
        this.utility = utility;
        this.userService = userService;
    }

    public String generateToken(String userId){
        return utility.generateToken(userId);
    }
}
