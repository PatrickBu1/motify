package com.cs4523groupb11.Motify.DTO.detailed_entity.abstraction;

import com.cs4523groupb11.Motify.DTO.detailed_entity.derived.QuantityWorkloadDTO;
import com.cs4523groupb11.Motify.DTO.detailed_entity.derived.TimeWorkloadDTO;
import com.cs4523groupb11.Motify.entities.abstraction.ChallengeWorkload;
import com.cs4523groupb11.Motify.entities.derived.QuantityWorkload;
import com.cs4523groupb11.Motify.entities.derived.TimeWorkload;

public abstract class ChallengeWorkloadDTO {
    protected Integer amount;

    public Integer getAmount() {return amount;}
    public void setAmount(Integer amount) { this.amount = amount;}

    public static ChallengeWorkloadDTO fromEntity(ChallengeWorkload cw){
        if (cw instanceof QuantityWorkload){
            return QuantityWorkloadDTO.fromEntity((QuantityWorkload) cw);
        }else{
            return TimeWorkloadDTO.fromEntity((TimeWorkload) cw);
        }
    }
}

