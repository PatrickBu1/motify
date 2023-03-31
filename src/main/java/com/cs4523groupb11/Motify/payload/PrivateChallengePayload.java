package com.cs4523groupb11.Motify.payload;

import com.cs4523groupb11.Motify.entities.ChallengeCategory;
import com.cs4523groupb11.Motify.entities.CheckInData;
import com.cs4523groupb11.Motify.entities.User;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

public class PrivateChallengePayload {
    private String id;
    private String owner;
    private String name;
    private String description;
    private String category;
    private Date startDate;
    private Date endDate;
    private Integer objective;
    private List<String> checkInDataList;

    public PrivateChallengePayload(){}
    public PrivateChallengePayload(String owner, String name, String description, String category, Date startDate,
                            Date endDate, Integer objective, List<String> checkInDataList){
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
        this.objective = objective;
        this.checkInDataList = checkInDataList;
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

    public String getCategory(){return category;}
    public void setCategory(String category){this.category = category;}

    public Date getStartDate(){
        return startDate;
    }
    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }

    public Date getEndDate(){
        return endDate;
    }
    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }

    public Integer getObjective(){
        return objective;
    }
    public void setObjective(Integer objective){
        this.objective = objective;
    }

    public List<String> getCheckInDataList(){
        return checkInDataList;
    }
    public void setCheckInDataList(List<String> checkInDataList){
        this.checkInDataList = checkInDataList;
    }
}
