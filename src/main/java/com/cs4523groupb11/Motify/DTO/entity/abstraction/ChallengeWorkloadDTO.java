package com.cs4523groupb11.Motify.DTO.entity.abstraction;


import com.cs4523groupb11.Motify.DTO.entity.QuantityWorkloadDTO;
import com.cs4523groupb11.Motify.DTO.entity.TimeWorkloadDTO;
import com.cs4523groupb11.Motify.entities.abstraction.ChallengeWorkload;
import com.cs4523groupb11.Motify.entities.derived.QuantityWorkload;
import com.cs4523groupb11.Motify.entities.derived.TimeWorkload;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type",
        defaultImpl = TimeWorkloadDTO.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = QuantityWorkloadDTO.class, name = "quantity"),
        @JsonSubTypes.Type(value = TimeWorkloadDTO.class, name = "time")
})

public abstract class ChallengeWorkloadDTO {

    public static ChallengeWorkloadDTO fromEntity(ChallengeWorkload cw){
        if (cw instanceof QuantityWorkload){
            return new QuantityWorkloadDTO(((QuantityWorkload) cw).getAmount(),
                    ((QuantityWorkload) cw).getUnit());
        }
        return new TimeWorkloadDTO(((TimeWorkload) cw).getDuration());
    }
}
