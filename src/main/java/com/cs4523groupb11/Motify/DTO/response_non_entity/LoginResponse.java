package com.cs4523groupb11.Motify.DTO.response_non_entity;

import com.cs4523groupb11.Motify.DTO.entity.UserDTO;

public class LoginResponse {
    private String token;
    private UserDTO user;

    public LoginResponse(){};

    public LoginResponse(String token, UserDTO user){
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

}
