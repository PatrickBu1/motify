package com.cs4523groupb11.Motify.payload;

public class SignupRequest {
    private String username;
    private String email;
    private String password;
    private int age;

    public SignupRequest(String username, String email, String password, int age){
        this.username = username;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    public String getUsername() {return username;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public int getAge() {return age;}

    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setAge(int age) {
        this.age = age;
    }

}
