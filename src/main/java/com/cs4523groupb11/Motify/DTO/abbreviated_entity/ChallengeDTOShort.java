package com.cs4523groupb11.Motify.DTO.abbreviated_entity;


import com.cs4523groupb11.Motify.DTO.detailed_entity.ChallengeDTO;
import com.cs4523groupb11.Motify.DTO.detailed_entity.abstraction.ChallengeContentDTO;
import com.cs4523groupb11.Motify.entities.Challenge;

public class ChallengeDTOShort {
    private String id;

    private String owner;

    private String name;

    private String description;

    private Boolean isPrivate;

    private String category;

    public ChallengeDTOShort(String id, String owner, String name,
                        String description, Boolean isPrivate, String category) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.isPrivate = isPrivate;
        this.category = category;
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

    public static ChallengeDTOShort fromEntity(Challenge c){
        return new ChallengeDTOShort(c.getId(), c.getOwner().getId(), c.getName(),
                c.getDescription(), c.getIsPrivate(), c.getCategory().toString());
    }
}
