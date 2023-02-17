package com.cs4523groupb11.Motify.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table("checkin_data")
public class CheckInData {
    private String id;
    private User owner;
    private String content;
    private Date date;
    private PrivateChallenge privateChallenge;
    private PublicChallenge publicChallenge;

    public CheckInData(){}
    public CheckInData(User owner, String content, Date date,
                       PrivateChallenge privateChallenge,
                       PublicChallenge publicChallenge){
        this.owner = owner;
        this.content = content;
        this.date = date;
        this.privateChallenge = privateChallenge;
        this.publicChallenge= publicChallenge;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    @ManyToOne()
    @JoinColumn(name = "owner", nullable = false)
    public User getOwner(){
        return owner;
    }
    public void setOwner(User owner){
        this.owner = owner;
    }

    @Column(name = "content")
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }

    @Column(name = "date", nullable = false)
    public Date getDate(){
        return date;
    }
    public void setDate(Date date){
        this.date  = date;
    }

    @ManyToOne()
    @JoinColumn(name = "private_challenge_id")
    public PrivateChallenge getPrivateChallenge(){
        return privateChallenge;
    }
    public void setPrivateChallenge(PrivateChallenge privateChallenge){
        this.privateChallenge = privateChallenge;
    }

    @ManyToOne()
    @JoinColumn(name = "public_challenge_id")
    public PublicChallenge getPublicChallenge(){
        return publicChallenge;
    }
    public void setPublicChallenge(PublicChallenge publicChallenge) {
        this.publicChallenge = publicChallenge;
    }
}
