package com.cs4523groupb11.Motify.controllers;

import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<User> findById(@PathVariable String id){
        Optional<User> user = userService.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<String> findByUsername(@PathVariable String username){
        Optional<String> user = userService.findByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<String> findByEmail(@PathVariable String email){
        Optional<String> user = userService.findByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User body){
        Optional<User> user = userService.create(body);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> delete(@PathVariable String id){
        Optional<User> user = userService.delete(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody User body){
        Optional<User> user = userService.update(body);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
