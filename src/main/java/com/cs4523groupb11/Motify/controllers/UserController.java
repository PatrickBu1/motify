package com.cs4523groupb11.Motify.controllers;

import com.cs4523groupb11.Motify.DTO.entity.UserDTO;
import com.cs4523groupb11.Motify.entities.User;
import com.cs4523groupb11.Motify.security.JwtTokenUtility;
import com.cs4523groupb11.Motify.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        String email = jwt.getFromHeader(auth);
        Optional<User> opUser = userService.findByEmail(email);
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
    public ResponseEntity<List<UserDTO>> getListByIds(@RequestBody List<String> idList){
        List<User> userList = userService.findListByIds(idList);
        if (userList.isEmpty()) {return ResponseEntity.notFound().build();}
        List<UserDTO> res = userList.stream().map(UserDTO::fromEntity).toList();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getAllByUsername/{username}")
    public ResponseEntity<List<UserDTO>> getAllByUsername(@PathVariable String username){
        List<User> userList = userService.findAllByUsername(username);
        return ResponseEntity.ok(userList.stream().map(UserDTO::fromEntity).toList());
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<UserDTO> getByEmail(@PathVariable String email){
        Optional<User> opUser = userService.findByEmail(email);
        return opUser.map(user -> ResponseEntity.ok(UserDTO.fromEntity(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/uploadUserProfileImage/{}")
    public ResponseEntity<?> setProfileImage(@RequestHeader(name = "Authorization") String auth,
                                             @RequestParam(value = "file") MultipartFile image){
        Optional<User> opUser = userService.setProfilePicture(auth, image);
        if (opUser.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(opUser.get());
    }

    @GetMapping("/testAuth")
    public ResponseEntity<?> testAuth(){
        return ResponseEntity.ok().build();
    }

}
