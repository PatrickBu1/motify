package com.cs4523groupb11.Motify.DTO.response_non_entity;

import java.util.List;

public class LoginResponse {
    private String token;
    private String userId;

    public LoginResponse(){};

    public LoginResponse(String token, String userId){
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
