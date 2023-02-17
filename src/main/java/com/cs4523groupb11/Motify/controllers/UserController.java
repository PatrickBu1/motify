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

    @GetMapping("/get")
    public ResponseEntity<User> getUserByUserId(@RequestParam String userId){
        final User user;
        try{
            user = userService.findByUserId(userId);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createUser(@RequestBody User body){
        try {
            userService.createUser(body);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@RequestParam String userId){
        try{
            userService.deleteUser(userId);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User body){
        return ResponseEntity.noContent().build();
    }

}
