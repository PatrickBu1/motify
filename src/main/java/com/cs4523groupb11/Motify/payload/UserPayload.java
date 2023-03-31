package com.cs4523groupb11.Motify.payload;

import com.cs4523groupb11.Motify.entities.PrivateChallenge;
import com.cs4523groupb11.Motify.entities.PublicChallenge;

import java.util.List;

public class UserPayload {
    private String id;

    private String username;

    private String email;

    private List<PrivateChallenge> privateChallengeList;

    private List<PublicChallenge> publicChallengeList;

    public UserPayload() {

    }

    public UserPayload(String username, String email,
                       List<PrivateChallenge> privateChallengeList,
                       List<PublicChallenge> publicChallengeList){
        this.username = username;
        this.email = email;
        this.privateChallengeList = privateChallengeList;
        this.publicChallengeList = publicChallengeList;
    }

    public String getId(){return id;}
    public void setId(String id){this.id = id;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public List<PrivateChallenge> getPrivateChallengeList(){
        return privateChallengeList;
    }
    public void setPrivateChallengeList(List<PrivateChallenge> privateChallengeList){
        this.privateChallengeList = privateChallengeList;
    }

    public List<PublicChallenge> getPublicChallengeList(){
        return publicChallengeList;
    }
    public void setPublicChallengeList(List<PrivateChallenge> privateChallengeList){
        this.privateChallengeList = privateChallengeList;
    }
}