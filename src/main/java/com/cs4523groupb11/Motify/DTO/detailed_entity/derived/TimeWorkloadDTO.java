package com.cs4523groupb11.Motify.DTO.detailed_entity.derived;


import com.cs4523groupb11.Motify.DTO.detailed_entity.abstraction.ChallengeWorkloadDTO;
import com.cs4523groupb11.Motify.entities.derived.TimeWorkload;


public class TimeWorkloadDTO extends ChallengeWorkloadDTO {

    private String timeUnit;


    public TimeWorkloadDTO(){}

    public TimeWorkloadDTO(Integer amount, String timeUnit){
        this.amount = amount;
        this.timeUnit = timeUnit;
    }


    public String getTimeUnit() {return timeUnit;}
    public void setTimeUnit(String timeUnit) { this.timeUnit = timeUnit;}

    public static TimeWorkloadDTO fromEntity(TimeWorkload qw){
        return new TimeWorkloadDTO(qw.getAmount(), qw.getTimeUnit().toString());
    }
}
