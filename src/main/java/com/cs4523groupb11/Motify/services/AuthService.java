package com.cs4523groupb11.Motify.services;

import com.cs4523groupb11.Motify.exceptions.UserExistsException;
import com.cs4523groupb11.Motify.payload.response.LoginResponse;

import java.util.List;
import java.util.Optional;

public interface AuthService {

    public Optional<LoginResponse> authenticateUser(String username, String password);

    public Optional<LoginResponse> registerUser(String username,  String email, String password, List<String> roles);

}
