package com.cs4523groupb11.Motify.DTO.detailed_entity.derived;


import com.cs4523groupb11.Motify.DTO.detailed_entity.abstraction.ChallengeContentDTO;
import com.cs4523groupb11.Motify.DTO.detailed_entity.abstraction.ChallengeWorkloadDTO;
import com.cs4523groupb11.Motify.entities.abstraction.ChallengeWorkload;
import com.cs4523groupb11.Motify.entities.derived.GoalContent;

public class GoalContentDTO extends ChallengeContentDTO {
    public GoalContentDTO(ChallengeWorkloadDTO workload){
        this.workload = workload;
    }

    public static GoalContentDTO fromEntity(GoalContent gc){
        ChallengeWorkloadDTO wlDTO = ChallengeWorkloadDTO.fromEntity(gc.getWorkload());
        return new GoalContentDTO(wlDTO);
    }
}
