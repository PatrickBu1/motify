package com.cs4523groupb11.Motify.DTO.entity;

import com.cs4523groupb11.Motify.entities.Participation;

import java.time.Duration;
import java.util.Date;
import java.util.List;

public class ParticipationDTO {

    private String ownerId;

    private String challenge;

    private Boolean isPrivate;

    private Boolean isGoal;

    private Boolean isActive;

    private Integer progress;

    private Duration durationProgress;

    private List<Date> completedDates;

    private Integer streak;

    public ParticipationDTO(){}

    public ParticipationDTO(String ownerId, String challenge, Boolean isPrivate, Boolean isGoal,
                            Boolean isActive, Integer progress, Duration durationProgress,
                            List<Date> completedDates, Integer streak){
        this.ownerId = ownerId;
        this.challenge = challenge;
        this.isPrivate = isPrivate;
        this.isGoal = isGoal;
        this.isActive = isActive;
        this.progress = progress;
        this.durationProgress = durationProgress;
        this.completedDates = completedDates;
        this.streak = streak;
    }

    public String getOwnerId() {return ownerId;}
    public void setOwnerId(String ownerId) {this.ownerId = ownerId;}

    public String getChallenge() {return challenge;}
    public void setChallenge(String challenge) {this.challenge = challenge;}

    public Boolean getPrivate() {return isPrivate;}
    public void setPrivate(Boolean aPrivate) {isPrivate = aPrivate;}

    public Boolean getIsGoal(){return isGoal;}
    public void setIsGoal(Boolean isGoal){this.isGoal = isGoal;}

    public Boolean getIsActive() {return isActive;}

    public void setIsActive(Boolean active) {isActive = active;}

    public Integer getProgress() {return progress;}
    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Duration getDurationProgress() {return durationProgress;}

    public void setDurationProgress(Duration durationProgress) {this.durationProgress = durationProgress;}

    public List<Date> getCompletedDates() {return completedDates;}
    public void setCompletedDates(List<Date> completedDates) {this.completedDates = completedDates;}

    public Integer getStreak() {return streak;}
    public void setStreak(Integer streak) {
        this.streak = streak;
    }

    public static ParticipationDTO fromEntity(Participation p){
        return new ParticipationDTO(p.getOwner().getId(),
                p.getChallenge().getId(),
                p.getIsPrivate(),
                p.getIsHabit(),
                p.getIsActive(),
                p.getProgress(),
                p.getDurationProgress(),
                p.getCompletedDates(),
                p.getStreak());
    }
}
