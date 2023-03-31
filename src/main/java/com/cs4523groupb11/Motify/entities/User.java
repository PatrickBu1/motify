package com.cs4523groupb11.Motify.entities;


import jakarta.persistence.*;
import org.hibernate.annotations.Check;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User{
    private String id;

    private String username;

    private String password;

    private String email;

    private Set<Role> roles = new HashSet<>();

    private List<PrivateChallenge> privateChallengeList;

    private List<PublicChallenge> publicChallengeList;

    private List<CheckInData> checkInDataList;

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

    @Column(name="email")
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @OneToMany(mappedBy = "users")
    public List<PrivateChallenge> getPrivateChallengeList(){
        return privateChallengeList;
    }
    public void setPrivateChallengeList(List<PrivateChallenge> privateChallengeList){
        this.privateChallengeList = privateChallengeList;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_public_challenges",
                joinColumns = @JoinColumn("user_id"),
                inverseJoinColumns = @JoinColumn("public_challenge_id"))
    public List<PublicChallenge> getPublicChallengeList(){
        return publicChallengeList;
    }
    public void setPublicChallengeList(List<PublicChallenge> publicChallengeList){
        this.publicChallengeList = publicChallengeList;
    }

    @OneToMany(mappedBy = "users")
    public List<CheckInData> getCheckInDataList(){
        return checkInDataList;
    }
    public void setCheckInDataList(List<CheckInData> checkInDataList){
        this.checkInDataList = checkInDataList;
    }
}
