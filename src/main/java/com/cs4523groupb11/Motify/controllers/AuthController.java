package com.cs4523groupb11.Motify.controllers;

import com.cs4523groupb11.Motify.payload.request.LoginRequest;
import com.cs4523groupb11.Motify.payload.request.SignupRequest;
import com.cs4523groupb11.Motify.payload.response.LoginResponse;
import com.cs4523groupb11.Motify.payload.response.MessageResponse;
import com.cs4523groupb11.Motify.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Validated @RequestBody SignupRequest signupRequest){
        String username = signupRequest.getUsername();
        String email = signupRequest.getEmail();
        String password = signupRequest.getPassword();
        List<String> roles = signupRequest.getRole();
        Optional<LoginResponse> response = authService.registerUser(username, email, password, roles);
        if (response.isPresent()){
            return ResponseEntity.ok(response.get());
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Username or Email Already Exists"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody LoginRequest loginRequest){
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        Optional<LoginResponse> response = authService.authenticateUser(username, password);
        if (response.isPresent()){
            return ResponseEntity.ok(response.get());
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Error: login failed"));
    }
}