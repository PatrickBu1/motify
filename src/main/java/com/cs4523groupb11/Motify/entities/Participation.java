package com.cs4523groupb11.Motify.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "participation",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"owner", "challenge"})})
public class Participation {
    private String id;

    private User owner;

    private Challenge challenge;

    private Boolean isPrivate;

    private Boolean isGoal;    // if goal, then based on progress; else it's based on completed dates and streak

    private Boolean isActive;

    private Integer progress;

    private List<Date> completedDates;

    private Integer streak;

    public Participation(){}

    public Participation(User owner, Challenge challenge, Boolean isGoal, Boolean isActive,
                         Integer progress, List<Date> completedDates, Integer streak){
        this.owner = owner;
        this.challenge = challenge;
        this.isGoal = isGoal;
        this.isActive = isActive;
        this.progress = progress;
        this.completedDates = completedDates;
        this.streak = streak;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String getId(){return id;}
    public void setId(String id){this.id = id;}

    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    public User getOwner() {return owner;}
    public void setOwner(User owner) {this.owner = owner;}

    @ManyToOne
    @JoinColumn(name = "challenge", nullable = false)
    public Challenge getChallenge() {return challenge;}
    public void setChallenge(Challenge challenge) {this.challenge = challenge;}

    @Column(name="is_private", nullable = false)
    public Boolean getIsPrivate() {return isPrivate;}
    public void setIsPrivate(Boolean aPrivate) {isPrivate = aPrivate;}

    @Column(name="is_progress_based", nullable = false)
    public Boolean getIsGoal(){return isGoal;}
    public void setIsGoal(Boolean isProgressBased){this.isGoal = isProgressBased;}

    @Column(name="is_active", nullable=false)
    public Boolean getIsActive() {return isActive;}
    public void setIsActive(Boolean active) {isActive = active;}

    @Column(name = "progress", nullable = false)
    public Integer getProgress() {return progress;}
    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    @ElementCollection
    @CollectionTable(name = "completed_dates")
    public List<Date> getCompletedDates() {return completedDates;}
    public void setCompletedDates(List<Date> completedDates) {this.completedDates = completedDates;}

    @Column(name = "streak", nullable = false)
    public Integer getStreak() {return streak;}
    public void setStreak(Integer streak) {
        this.streak = streak;
    }
}
