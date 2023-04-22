package com.cs4523groupb11.Motify.controllers;

import com.cs4523groupb11.Motify.DTO.request_non_entity.LoginRequest;
import com.cs4523groupb11.Motify.DTO.request_non_entity.SignupRequest;
import com.cs4523groupb11.Motify.DTO.response_non_entity.LoginResponse;
import com.cs4523groupb11.Motify.DTO.response_non_entity.MessageResponse;
import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody LoginRequest loginRequest){
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        Optional<LoginResponse> response = authService.authenticateUser(email, password);
        if (response.isPresent()){
            return ResponseEntity.ok(response.get());
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Error: login failed"));
    }

    @PostMapping("/signup")
    public ResponseEntity<LoginResponse> signup(@Validated @RequestBody SignupRequest signupRequest){
        String username = signupRequest.getUsername();
        String email = signupRequest.getEmail();
        String password = signupRequest.getPassword();
        System.out.println(username + " " +  email + " " +  password);
        // List<String> roles = signupRequest.getRole();
        Optional<LoginResponse> res = authService.registerUser(username, email, password, null);
        return res.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}