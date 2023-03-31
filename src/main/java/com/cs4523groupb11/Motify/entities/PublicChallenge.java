package com.cs4523groupb11.Motify.entities;


import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "public_challenges",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"owner", "name"})} )
public class PublicChallenge {
    private String id;
    private User owner;
    private String name;
    private String description;
    private ChallengeCategory category;
    private Date startDate;
    private Date endDate;
    private Integer objective;
    private List<CheckInData> checkInDataList;
    private List<User> participants;

    public PublicChallenge(){}

    public PublicChallenge(User owner, String name, String description, ChallengeCategory category,
                           Date startDate, Date endDate, Integer objective,
                           List<CheckInData> checkInDataList, List<User> participants){
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
        this.objective = objective;
        this.checkInDataList = checkInDataList;
        this.participants = participants;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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

    @Column(name="name", nullable = false)
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    @Column(name="description")
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    @Column(name="category")
    public ChallengeCategory getCategory(){return category;}
    public void setCategory(ChallengeCategory category){this.category = category;}

    @Column(name="start_date")
    public Date getStartDate(){
        return startDate;
    }
    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }

    @Column(name="end_date")
    public Date getEndDate(){
        return endDate;
    }
    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }

    @Column(name="objective")
    public Integer getObjective(){
        return objective;
    }
    public void setObjective(Integer objective){
        this.objective = objective;
    }

    @OneToMany(mappedBy = "public_challenges")
    public List<CheckInData> getCheckInDataList(){
        return checkInDataList;
    }
    public void setCheckInDataList(List<CheckInData> checkInDataList){
        this.checkInDataList = checkInDataList;
    }

    @ManyToMany(mappedBy="public_challenges")
    public List<User> getParticipants(){
        return participants;
    }
    public void setParticipants(List<User> participants){
        this.participants = participants;
    }
}
