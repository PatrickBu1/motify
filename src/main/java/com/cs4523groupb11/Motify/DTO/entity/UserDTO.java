package com.cs4523groupb11.Motify.DTO.entity;

import com.cs4523groupb11.Motify.entities.User;

public class UserDTO {
    private String id;

    private String username;

    private String email;

    private String profileImagePath;


    public UserDTO() {}

    public UserDTO(String id, String username, String email, String profileImagePath){
        this.id = id;
        this.username = username;
        this.email = email;
        this.profileImagePath = profileImagePath;
    }

    public String getId(){return id;}
    public void setId(String id){this.id = id;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public static UserDTO fromEntity(User u){
        return new UserDTO(u.getId(), u.getUsername(), u.getEmail(),
                u.getProfileImagePath());
    }

}