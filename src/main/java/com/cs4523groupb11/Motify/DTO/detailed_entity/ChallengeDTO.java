package com.cs4523groupb11.Motify.DTO.detailed_entity;

import com.cs4523groupb11.Motify.DTO.detailed_entity.abstraction.ChallengeContentDTO;
import com.cs4523groupb11.Motify.entities.Challenge;

import java.util.Date;
import java.util.List;


public class ChallengeDTO {
    private String id;

    private String owner;

    private String name;

    private String description;

    private Boolean isPrivate;

    private String category;

    private ChallengeContentDTO content;

    private Date createdAt;

    public ChallengeDTO(String id, String owner, String name,
                        String description, Boolean isPrivate, String category,
                        ChallengeContentDTO content, Date createdAt) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.isPrivate = isPrivate;
        this.category = category;
        this.content = content;
        this.createdAt = createdAt;
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

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public Boolean getPrivate() {return isPrivate;}
    public void setPrivate(Boolean aPrivate) {isPrivate = aPrivate;}

    public String getCategory(){return category;}
    public void setCategory(String category){this.category = category;}

    public ChallengeContentDTO getContent() {return content;}
    public void setContent(ChallengeContentDTO content) {this.content = content;}

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public static ChallengeDTO fromEntity(Challenge c){
        return new ChallengeDTO(c.getId(), c.getOwner().getId(), c.getName(),
                c.getDescription(), c.getIsPrivate(), c.getCategory().toString(),
                ChallengeContentDTO.fromEntity(c.getContent()), c.getCreatedAt());
    }


}