package com.cs4523groupb11.Motify.DTO.entity;


import com.cs4523groupb11.Motify.DTO.entity.abstraction.ChallengeWorkloadDTO;
import com.cs4523groupb11.Motify.entities.Challenge;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Date;


public class ChallengeDTO {
    private String id;

    private String ownerUsername;

    private String ownerId;

    private String name;

    private String description;

    private Boolean isPrivate;

    private String category;

    private Boolean isOngoing;

    private Date startDate;

    private Date endDate;

    private String frequency; // Habit-challenge only; nullable for Goal-challenges

    private ChallengeWorkloadDTO workload;

    private Date createdAt;


    public ChallengeDTO(){}

    public ChallengeDTO(String id, String ownerUsername, String ownerId, String name, String description,
                        Boolean isPrivate, String category, Boolean isOngoing, Date startDate,
                        Date endDate, String frequency, ChallengeWorkloadDTO workload,
                        Date createdAt){
        this.id = id;
        this.ownerUsername = ownerUsername;
        this.ownerId = ownerId;
        this.name = name;
        this.description = description;
        this.isPrivate = isPrivate;
        this.category = category;
        this.isOngoing = isOngoing;
        this.startDate = startDate;
        this.endDate = endDate;
        this.frequency = frequency;
        this.workload = workload;
        this.createdAt = createdAt;
    }


    public String getId(){return id;}
    public void setId(String id){this.id = id;}

    public String getOwnerUsername(){return ownerUsername;}
    public void setOwnerUsername(String ownerUsername){this.ownerUsername = ownerUsername;}

    public String getOwnerId() {return ownerId;}

    public void setOwnerId(String ownerId) {this.ownerId = ownerId;}

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

    public Boolean getIsPrivate() { return isPrivate;};
    public void setIsPrivate(Boolean isPrivate){this.isPrivate = isPrivate;}

    public String getCategory(){return category;}
    public void setCategory(String category){this.category = category;}

    public Boolean getIsOngoing() {return isOngoing;}
    public void setIsOngoing(Boolean ongoing) {isOngoing = ongoing;}

    public Date getStartDate() {return startDate;}
    public void setStartDate(Date startDate) {this.startDate = startDate;}

    public Date getEndDate() {return endDate;}
    public void setEndDate(Date endDate) {this.endDate = endDate;}

    public String getFrequency() {return frequency;}
    public void setFrequency(String frequency) {this.frequency = frequency;}

    @JsonProperty("workload")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = QuantityWorkloadDTO.class, name = "quantity"),
            @JsonSubTypes.Type(value = TimeWorkloadDTO.class, name = "time")
    })
    public ChallengeWorkloadDTO getWorkload() {return workload;}
    public void setWorkload(ChallengeWorkloadDTO workload) {this.workload = workload;}

    public Date getCreatedAt() { return createdAt;}
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt;}

    public static ChallengeDTO fromEntity(Challenge c){
        return new ChallengeDTO(c.getId(), c.getOwner().getUsername(), c.getOwner().getId(), c.getName(),
                c.getDescription(), c.getIsPrivate(), c.getCategory().toString(), c.getIsOngoing(),
                c.getStartDate(), c.getEndDate(), (c.getFrequency() == null)? null : c.getFrequency().toString(),
                ChallengeWorkloadDTO.fromEntity(c.getWorkload()), c.getCreatedAt());
    }


}