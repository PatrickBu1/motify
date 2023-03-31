package com.cs4523groupb11.Motify.payload.response;

import java.util.List;

public class LoginResponse {
    private String token;
    private String userId;
    private String username;
    private String email;

    private List<String> roles;

    public LoginResponse(){};

    public LoginResponse(String token, String userId, String username, String email, List<String> roles){
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.roles = roles;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
