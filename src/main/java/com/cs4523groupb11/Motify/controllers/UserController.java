package com.cs4523groupb11.Motify.controllers;

import com.cs4523groupb11.Motify.DTO.detailed_entity.UserDTO;
import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.security.JwtTokenUtility;
import com.cs4523groupb11.Motify.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserController {

    private final UserService userService;

    private final JwtTokenUtility jwt;

    @Autowired
    public UserController(UserService userService, JwtTokenUtility jwt){
        this.userService = userService;
        this.jwt = jwt;
    }


    @GetMapping("/getSelf")
    public ResponseEntity<UserDTO> getSelf(@RequestHeader(name="Authorization") String auth){
        String username = jwt.getUsernameFromJwtToken(jwt.getFromHeader(auth));
        Optional<User> opUser = userService.findByUsername(username);
        if (opUser.isPresent()) {
            UserDTO response = UserDTO.fromEntity(opUser.get());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/getOneById/{id}")
    public ResponseEntity<UserDTO> getOneById(@PathVariable String id){
        Optional<User> opUser = userService.findById(id);
        return opUser.map(user -> ResponseEntity.ok( UserDTO.fromEntity(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getListByIds")
    public ResponseEntity<List<UserDTO>> getListById(@RequestBody List<String> idList){
        List<User> userList = userService.findListByIds(idList);
        if (userList.isEmpty()) {return ResponseEntity.notFound().build();}
        List<UserDTO> res = userList.stream().map(UserDTO::fromEntity).toList();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<UserDTO> findByUsername(@PathVariable String username){
        Optional<User> opUser = userService.findByUsername(username);
        return opUser.map(user -> ResponseEntity.ok(UserDTO.fromEntity(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/findOtherByEmail/{email}")
    public ResponseEntity<UserDTO> findByEmail(@PathVariable String email){
        Optional<User> opUser = userService.findByEmail(email);
        return opUser.map(user -> ResponseEntity.ok(UserDTO.fromEntity(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
