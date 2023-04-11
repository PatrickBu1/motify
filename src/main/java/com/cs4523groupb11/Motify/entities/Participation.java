package com.cs4523groupb11.Motify.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "participation",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user", "challenge"})})
public class Participation {
    private String id;

    private User user;

    private Challenge challenge;

    private Boolean isProgressBased;    // if not progress-based, then it's based on completed dates nad streak

    private Integer progress;

    private List<Date> completedDates;

    private Integer streak;

    public Participation(){}

    public Participation(User user, Challenge challenge, Boolean isProgressBased,
                         Integer progress, List<Date> completedDates, Integer streak){
        this.user = user;
        this.challenge = challenge;
        this.isProgressBased = isProgressBased;
        this.progress = progress;
        this.completedDates = completedDates;
        this.streak = streak;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String getId(){return id;}
    public void setId(String id){this.id = id;}

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}

    @ManyToOne
    @JoinColumn(name = "challenge", nullable = false)
    public Challenge getChallenge() {return challenge;}
    public void setChallenge(Challenge challenge) {this.challenge = challenge;}

    @Column(name="is_progress_based", nullable = false)
    public Boolean getIsProgressBased(){return isProgressBased;}
    public void setIsProgressBased(Boolean isProgressBased){this.isProgressBased = isProgressBased;}

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
