package com.cs4523groupb11.Motify.DTO.entity;

import com.cs4523groupb11.Motify.DTO.entity.abstraction.ChallengeWorkloadDTO;

import java.time.Duration;

public class TimeWorkloadDTO extends ChallengeWorkloadDTO {

    private Duration duration;

    public TimeWorkloadDTO(){}

    public TimeWorkloadDTO(Duration duration){
        this.duration = duration;
    }

    public Duration getDuration() {return duration;}
    public void setDuration(Duration duration) {this.duration = duration;}


}
