package com.cs4523groupb11.Motify.payload;

import com.cs4523groupb11.Motify.entities.PrivateChallenge;
import com.cs4523groupb11.Motify.entities.PublicChallenge;
import com.cs4523groupb11.Motify.entities.User;
import jakarta.persistence.*;

import java.util.Date;

public class CheckInDataPayload {
    private String id;
    private String owner;
    private String content;
    private Date date;
    private String privateChallenge;
    private String publicChallenge;

    public CheckInDataPayload(){}
    public CheckInDataPayload(String owner, String content, Date date,
                              String privateChallenge,
                              String publicChallenge){
        this.owner = owner;
        this.content = content;
        this.date = date;
        this.privateChallenge = privateChallenge;
        this.publicChallenge= publicChallenge;
    }

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getOwner(){
        return owner;
    }
    public void setOwner(String owner){
        this.owner = owner;
    }

    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }

    public Date getDate(){
        return date;
    }
    public void setDate(Date date){
        this.date  = date;
    }

    public String getPrivateChallenge(){
        return privateChallenge;
    }
    public void setPrivateChallenge(String privateChallenge){
        this.privateChallenge = privateChallenge;
    }

    public String getPublicChallenge(){
        return publicChallenge;
    }
    public void setPublicChallenge(String publicChallenge) {
        this.publicChallenge = publicChallenge;
    }
}

