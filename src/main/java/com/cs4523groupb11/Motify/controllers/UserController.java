package com.cs4523groupb11.Motify.controllers;

import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User body){
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@RequestParam String userId){
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/modify")
    public ResponseEntity<?> modifyUser(@RequestBody User body){
        return ResponseEntity.noContent().build();
    }

}
