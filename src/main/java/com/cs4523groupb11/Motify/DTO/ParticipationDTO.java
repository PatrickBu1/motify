package com.cs4523groupb11.Motify.DTO;

import com.cs4523groupb11.Motify.entities.Participation;
import com.cs4523groupb11.Motify.entities.User;

import java.util.Date;
import java.util.List;

public class ParticipationDTO {

    private String user;

    private String challenge;

    private Boolean isProgressBased;

    private Integer progress;

    private List<Date> completedDates;

    private Integer streak;

    public ParticipationDTO(){}

    public ParticipationDTO(String user, String challenge, Boolean isProgressBased,
                         Integer progress, List<Date> completedDates, Integer streak){
        this.user = user;
        this.challenge = challenge;
        this.isProgressBased = isProgressBased;
        this.progress = progress;
        this.completedDates = completedDates;
        this.streak = streak;
    }

    public String getUser() {return user;}
    public void setUser(String user) {this.user = user;}

    public String getChallenge() {return challenge;}
    public void setChallenge(String challenge) {this.challenge = challenge;}

    public Boolean getIsProgressBased(){return isProgressBased;}
    public void setIsProgressBased(Boolean isProgressBased){this.isProgressBased = isProgressBased;}

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
                p.getIsProgressBased(),
                p.getProgress(),
                p.getCompletedDates(),
                p.getStreak());
    }
}
