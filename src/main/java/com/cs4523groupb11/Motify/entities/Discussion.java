package com.cs4523groupb11.Motify.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="discussion",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"owner", "challenge", "date"})})
public class Discussion {
    private String id;

    private User owner;

    private String content;

    private Date date;

    private Challenge challenge;

    private String imagePath;

    public Discussion(){}
    public Discussion(User owner, String content,
                      Date date, Challenge challenge,
                      String imagePath){
        this.owner = owner;
        this.content = content;
        this.date = date;
        this.challenge = challenge;
        this.imagePath = imagePath;
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


    @Column(name = "content", nullable = false)
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
    @JoinColumn(name = "challenge")
    public Challenge getChallenge(){
        return challenge;
    }
    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    @Column(name = "image_path")
    public String getImagePath() {return imagePath;}
    public void setImagePath(String imagePath) {this.imagePath = imagePath;}
}
