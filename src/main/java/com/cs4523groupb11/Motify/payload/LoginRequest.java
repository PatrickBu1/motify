package com.cs4523groupb11.Motify.payload;

public class LoginRequest {
    private String username;
    private String password;

    public LoginRequest(String email, String password){
        this.username = email;
        this.password = password;
    }

    public String getUsername(){return username;}
    public String getPassword(){return password;}

}
