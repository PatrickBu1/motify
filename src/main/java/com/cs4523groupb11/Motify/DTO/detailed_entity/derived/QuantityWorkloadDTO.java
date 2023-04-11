package com.cs4523groupb11.Motify.DTO.detailed_entity.derived;


import com.cs4523groupb11.Motify.DTO.detailed_entity.abstraction.ChallengeWorkloadDTO;
import com.cs4523groupb11.Motify.entities.derived.QuantityWorkload;

public class QuantityWorkloadDTO extends ChallengeWorkloadDTO {

    private String unit;

    public QuantityWorkloadDTO(){}

    public QuantityWorkloadDTO(Integer amount, String unit){
        this.amount = amount;
        this.unit = unit;
    }


    public String getUnit() {return unit;}
    public void setUnit(String unit) { this.unit = unit;}

    public static QuantityWorkloadDTO fromEntity(QuantityWorkload qw){
        return new QuantityWorkloadDTO(qw.getAmount(), qw.getUnit());
    }
}