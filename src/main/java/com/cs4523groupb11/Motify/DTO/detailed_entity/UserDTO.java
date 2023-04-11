package com.cs4523groupb11.Motify.DTO.detailed_entity;

import com.cs4523groupb11.Motify.entities.User;

import java.util.List;

public class UserDTO {
    private String id;

    private String username;

    private String email;


    public UserDTO() {}

    public UserDTO(String id, String username, String email){
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public String getId(){return id;}
    public void setId(String id){this.id = id;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public static UserDTO fromEntity(User u){
        return new UserDTO(u.getId(), u.getUsername(), u.getEmail());
    }

}