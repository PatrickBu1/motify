package com.cs4523groupb11.Motify.DTO.detailed_entity;

import com.cs4523groupb11.Motify.entities.Participation;

import java.util.Date;
import java.util.List;

public class ParticipationDTO {

    private String user;

    private String challenge;

    private Boolean isPrivate;

    private Boolean isGoal;

    private Boolean isActive;

    private Integer progress;

    private List<Date> completedDates;

    private Integer streak;

    public ParticipationDTO(){}

    public ParticipationDTO(String user, String challenge, Boolean isPrivate, Boolean isGoal,
                         Boolean isActive, Integer progress, List<Date> completedDates, Integer streak){
        this.user = user;
        this.challenge = challenge;
        this.isPrivate = isPrivate;
        this.isGoal = isGoal;
        this.isActive = isActive;
        this.progress = progress;
        this.completedDates = completedDates;
        this.streak = streak;
    }

    public String getUser() {return user;}
    public void setUser(String user) {this.user = user;}

    public String getChallenge() {return challenge;}
    public void setChallenge(String challenge) {this.challenge = challenge;}

    public Boolean getPrivate() {return isPrivate;}
    public void setPrivate(Boolean aPrivate) {isPrivate = aPrivate;}

    public Boolean getIsGoal(){return isGoal;}
    public void setIsGoal(Boolean isGoal){this.isGoal = isGoal;}

    public Boolean getIsActive() {return isActive;}

    public void setIsActive(Boolean active) {isActive = active;}

    public Integer getCompletionStatus() {return progress;}
    public void setCompletionStatus(Integer progress) {
        this.progress = progress;
    }

    public List<Date> getCompletedDates() {return completedDates;}
    public void setCompletedDates(List<Date> completedDates) {this.completedDates = completedDates;}

    public Integer getStreak() {return streak;}
    public void setStreak(Integer streak) {
        this.streak = streak;
    }

    public static ParticipationDTO fromEntity(Participation p){
        return new ParticipationDTO(p.getUser().getId(),
                p.getChallenge().getId(),
                p.getIsPrivate(),
                p.getIsGoal(),
                p.getIsActive(),
                p.getProgress(),
                p.getCompletedDates(),
                p.getStreak());
    }
}
