package com.cs4523groupb11.Motify.DTO.entity;

import com.cs4523groupb11.Motify.DTO.entity.abstraction.ChallengeWorkloadDTO;

public class QuantityWorkloadDTO extends ChallengeWorkloadDTO {
    private Integer amount;

    private String unit;

    public QuantityWorkloadDTO(){}

    public QuantityWorkloadDTO(Integer amount, String unit) {
        this.amount = amount;
        this.unit = unit;
    }


    public Integer getAmount() {return amount;}

    public void setAmount(Integer amount) {this.amount = amount;}

    public String getUnit() {return unit;}

    public void setUnit(String unit) {this.unit = unit;}
}
