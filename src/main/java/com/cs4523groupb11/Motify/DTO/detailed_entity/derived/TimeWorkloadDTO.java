package com.cs4523groupb11.Motify.DTO.detailed_entity.derived;


import com.cs4523groupb11.Motify.DTO.detailed_entity.abstraction.ChallengeWorkloadDTO;
import com.cs4523groupb11.Motify.entities.derived.QuantityWorkload;
import com.cs4523groupb11.Motify.entities.derived.TimeWorkload;


public class TimeWorkloadDTO extends ChallengeWorkloadDTO {

    private String unit;


    public TimeWorkloadDTO(){}

    public TimeWorkloadDTO(Integer amount, String unit){
        this.amount = amount;
        this.unit = unit;
    }


    public String getUnit() {return unit;}
    public void setUnit(String unit) { this.unit = unit;}

    public static TimeWorkloadDTO fromEntity(TimeWorkload qw){
        return new TimeWorkloadDTO(qw.getAmount(), qw.getUnit().toString());
    }
}
