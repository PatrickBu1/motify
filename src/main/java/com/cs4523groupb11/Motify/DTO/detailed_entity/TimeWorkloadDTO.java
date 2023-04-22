package com.cs4523groupb11.Motify.DTO.detailed_entity;

import com.cs4523groupb11.Motify.DTO.detailed_entity.abstraction.ChallengeWorkloadDTO;

import java.time.Duration;
import java.time.Period;

public class TimeWorkloadDTO extends ChallengeWorkloadDTO {
    private Period period;

    private Duration duration;

    public TimeWorkloadDTO(){}

    public TimeWorkloadDTO(Period period, Duration duration){
        this.period = period;
        this.duration = duration;
    }

    public Period getPeriod() {return period;}
    public void setPeriod(Period period) { this.period = period;}

    public Duration getDuration() {return duration;}
    public void setDuration(Duration duration) {this.duration = duration;}


}
