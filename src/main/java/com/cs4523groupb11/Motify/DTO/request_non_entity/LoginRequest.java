package com.cs4523groupb11.Motify.DTO.request_non_entity;

public class LoginRequest {
    private String email;
    private String password;

    public LoginRequest(){}
    public LoginRequest(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail(){return email;}
    public String getPassword(){return password;}

}
