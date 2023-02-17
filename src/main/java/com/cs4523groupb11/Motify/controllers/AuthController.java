package com.cs4523groupb11.Motify.controllers;

import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.payload.SignupRequest;
import com.cs4523groupb11.Motify.services.AuthService;
import com.cs4523groupb11.Motify.services.UserService;
import com.cs4523groupb11.Motify.payload.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;
    private UserService userService;
    // private PasswordEncoder encoder;

    @Autowired
    public AuthController(AuthService authService, UserService userService){
        this.authService = authService;
        this.userService = userService;
        // this.encoder = encoder;
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest){
        String username = signupRequest.getUsername();
        String email = signupRequest.getEmail();
        String password = signupRequest.getPassword();
        if (userService.findByUsername(username) != null) {
            return ResponseEntity.badRequest()
                    .body("Error: Username already exists!");
        }
        if (userService.findByEmail(email) != null) {
            return ResponseEntity.badRequest()
                    .body("Error: Email already exists!");
        }
        User user = userService.createUser(new User(username, password, email));
        String token = authService.generateToken(user.getId());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginData){
        String username = loginData.getUsername();
        String password = loginData.getPassword();
        User user = userService.findByUsernameAndPassword(username, password);
        if (user == null){
            return ResponseEntity.badRequest()
                    .body("Error: Username or password is incorrect!");
        }
        String token = authService.generateToken(user.getId());
        return ResponseEntity.ok(token);
    }
}