package com.cs4523groupb11.Motify.entities;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.Period;
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

    private Boolean isHabit;    // if Habit, completed dates and streak not null; if Goal, progress is not null.

    private Boolean isActive;

    private Boolean isQuantity;

    private Integer progress;

    private Duration durationProgress;

    private List<Date> completedDates;

    private Integer streak;

    public Participation(){}

    public Participation(User owner, Challenge challenge, Boolean isPrivate, Boolean isHabit, Boolean isActive, Boolean isQuantity,
                         Integer progress, Duration durationProgress, List<Date> completedDates, Integer streak){
        this.owner = owner;
        this.challenge = challenge;
        this.isPrivate = isPrivate;
        this.isHabit = isHabit;
        this.isActive = isActive;
        this.isQuantity = isQuantity;
        this.progress = progress;
        this.durationProgress = durationProgress;
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

    @Column(name="is_habit", nullable = false)
    public Boolean getIsHabit(){return isHabit;}
    public void setIsHabit(Boolean isHabit){this.isHabit = isHabit;}

    @Column(name="is_active", nullable=false)
    public Boolean getIsActive() {return isActive;}
    public void setIsActive(Boolean active) {isActive = active;}

    @Column(name="is_quantity")
    public Boolean getIsQuantity() {return isQuantity;}
    public void setIsQuantity(Boolean isQuantity) {this.isQuantity = isQuantity;}

    @Column(name = "progress")
    public Integer getProgress() {return progress;}
    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    @Column(name = "duration_progress")
    public Duration getDurationProgress() {return durationProgress;}
    public void setDurationProgress(Duration durationProgress) {
        this.durationProgress = durationProgress;
    }


    @ElementCollection
    @CollectionTable(name = "completed_dates")
    public List<Date> getCompletedDates() {return completedDates;}
    public void setCompletedDates(List<Date> completedDates) {this.completedDates = completedDates;}

    @Column(name = "streak")
    public Integer getStreak() {return streak;}
    public void setStreak(Integer streak) {
        this.streak = streak;
    }
}
