package com.cs4523groupb11.Motify.DTO.detailed_entity;


import com.cs4523groupb11.Motify.entities.CheckInData;

import java.util.Date;

public class CheckInDataDTO {
    private String id;
    private String username;
    private String title;
    private String content;
    private Date date;
    private String challenge;

    public CheckInDataDTO(){}
    public CheckInDataDTO(String id, String user, String title, String content,
                       Date date, String challenge){
        this.id = id;
        this.username = user;
        this.title = title;
        this.content = content;
        this.date = date;
        this.challenge = challenge;
    }


    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getUser(){
        return username;
    }
    public void setOwner(String user){
        this.username = user;
    }

    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}

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

    public String getChallenge(){
        return challenge;
    }
    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public static CheckInDataDTO fromEntity(CheckInData c){
        return new CheckInDataDTO(c.getId(), c.getOwner().getUsername(), c.getTitle(),
                c.getContent(), c.getDate(), c.getChallenge().getId());
    }
}

