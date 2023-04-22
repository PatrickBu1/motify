package com.cs4523groupb11.Motify.DTO.entity;


import com.cs4523groupb11.Motify.entities.Discussion;

import java.util.Date;

public class DiscussionDTO {
    private String id;

    private String ownerUsername;

    private String ownerId;

    private String content;

    private Date date;

    private String challengeId;

    private String imagePath;

    public DiscussionDTO(){}
    public DiscussionDTO(String id, String ownerUsername, String ownerId, String content,
                         Date date, String challengeId, String imagePath){
        this.id = id;
        this.ownerUsername = ownerUsername;
        this.ownerId = ownerId;
        this.content = content;
        this.date = date;
        this.challengeId = challengeId;
        this.imagePath = imagePath;
    }

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getOwnerUsername(){
        return ownerUsername;
    }
    public void setOwnerUsername(String ownerUsername){
        this.ownerUsername = ownerUsername;
    }

    public String getOwnerId() {return ownerId;}
    public void setOwnerId(String ownerId) {this.ownerId = ownerId;}

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

    public String getChallengeId(){
        return challengeId;
    }
    public void setChallengeId(String challengeId) {
        this.challengeId = challengeId;
    }

    public String getImagePath() {return imagePath;}
    public void setImagePath(String imagePath) {this.imagePath = imagePath;}

    public static DiscussionDTO fromEntity(Discussion c){
        return new DiscussionDTO(c.getId(), c.getOwner().getUsername(), c.getOwner().getId(), c.getContent(),
                c.getDate(), c.getChallenge().getId(), c.getImagePath());
    }
}

