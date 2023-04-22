package com.cs4523groupb11.Motify.entities;


import com.cs4523groupb11.Motify.entities.abstraction.ChallengeWorkload;
import com.cs4523groupb11.Motify.entities.enums.ChallengeCategory;
import com.cs4523groupb11.Motify.entities.enums.TimeUnit;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="challenge",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"owner", "name"})})
public class Challenge {
    private String id;

    private User owner;

    private String name;

    private String description;

    private Boolean isPrivate;

    private ChallengeCategory category;

    private Boolean isOngoing;

    private Date startDate;

    private Date endDate;

    private TimeUnit frequency; // Habit-challenge only; nullable for Goal-challenges

    private ChallengeWorkload workload;

    private Date createdAt;


    public Challenge(){}

    public Challenge(User owner, String name, String description, Boolean isPrivate,
                     ChallengeCategory category, Boolean isOngoing, Date startDate,
                     Date endDate, TimeUnit frequency, ChallengeWorkload workload,
                     Date createdAt){
        this.owner = owner;
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


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String getId(){return id;}
    public void setId(String id){this.id = id;}

    @ManyToOne()
    @JoinColumn(name = "owner", nullable = false)
    public User getOwner(){return owner;}
    public void setOwner(User owner){this.owner = owner;}

    @Column(name="name", nullable = false)
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    @Column(name="description", nullable = false)
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    @Column(name="is_private", nullable = false)
    public Boolean getIsPrivate() { return isPrivate;};
    public void setIsPrivate(Boolean isPrivate){this.isPrivate = isPrivate;}

    @Column(name="category", nullable = false)
    public ChallengeCategory getCategory(){return category;}
    public void setCategory(ChallengeCategory category){this.category = category;}

    @Column(name = "is_ongoing", nullable = false)
    public Boolean getIsOngoing() {return isOngoing;}
    public void setIsOngoing(Boolean ongoing) {isOngoing = ongoing;}

    @Column(name="start_date")
    public Date getStartDate() {return startDate;}
    public void setStartDate(Date startDate) {this.startDate = startDate;}

    @Column(name="end_date")
    public Date getEndDate() {return endDate;}
    public void setEndDate(Date endDate) {this.endDate = endDate;}

    @Column(name = "frequency")
    public TimeUnit getFrequency() {return frequency;}
    public void setFrequency(TimeUnit frequency) {this.frequency = frequency;}

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "workload")
    public ChallengeWorkload getWorkload() {return workload;}
    public void setWorkload(ChallengeWorkload workload) {
        this.workload = workload;
    }

    @Column(name = "created_at", nullable = false)
    public Date getCreatedAt() { return createdAt;}
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt;}

}
