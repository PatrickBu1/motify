package com.cs4523groupb11.Motify.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="checkin_data",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user", "challenge", "date"})})
public class CheckInData {
    private String id;
    private User user;
    private String title;
    private String content;
    private Date date;
    private Challenge challenge;

    public CheckInData(){}
    public CheckInData(User user, String title, String content,
                       Date date, Challenge challenge){
        this.user = user;
        this.title = title;
        this.content = content;
        this.date = date;
        this.challenge = challenge;
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
    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }

    @Column(name = "title", nullable = false)
    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}

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
    @JoinColumn(name = "challenge_id")
    public Challenge getChallenge(){
        return challenge;
    }
    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }
}
