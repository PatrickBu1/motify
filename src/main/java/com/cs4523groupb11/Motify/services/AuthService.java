package com.cs4523groupb11.Motify.services;

import com.cs4523groupb11.Motify.DTO.response_non_entity.LoginResponse;
import com.cs4523groupb11.Motify.entities.User;

import java.util.List;
import java.util.Optional;

public interface AuthService {

    Optional<LoginResponse> authenticateUser(String username, String password);

    Optional<LoginResponse> registerUser(String username, String email, String password, List<String> roles);

}
