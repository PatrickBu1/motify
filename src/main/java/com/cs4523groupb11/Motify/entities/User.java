package com.cs4523groupb11.Motify.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User{
    private String id;

    private String username;

    private String password;

    private String email;

    private String profileImagePath;

    private Set<Role> roles = new HashSet<>();

    public User() {

    }

    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String getId(){return id;}
    public void setId(String id){this.id = id;}


    @Column(name="username", nullable = false)
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    @Column(name="password", nullable = false)
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    @Column(name="email", nullable = false)
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    @Column(name = "profile_image_path")
    public String getProfileImagePath() {return profileImagePath;}
    public void setProfileImagePath(String profileImagePath) {this.profileImagePath = profileImagePath;}
}
