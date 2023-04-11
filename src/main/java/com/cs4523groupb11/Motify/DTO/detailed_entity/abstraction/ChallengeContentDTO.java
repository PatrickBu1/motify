package com.cs4523groupb11.Motify.DTO.detailed_entity.abstraction;

import com.cs4523groupb11.Motify.DTO.detailed_entity.derived.GoalContentDTO;
import com.cs4523groupb11.Motify.DTO.detailed_entity.derived.HabitContentDTO;
import com.cs4523groupb11.Motify.entities.abstraction.ChallengeContent;
import com.cs4523groupb11.Motify.entities.derived.GoalContent;
import com.cs4523groupb11.Motify.entities.derived.HabitContent;

public abstract class ChallengeContentDTO {
    protected ChallengeWorkloadDTO workload;

    public ChallengeWorkloadDTO getWorkload() {return workload;}
    public void setWorkload(ChallengeWorkloadDTO workload) {
        this.workload = workload;
    }

    public static ChallengeContentDTO fromEntity(ChallengeContent cc){
        if (cc instanceof HabitContent){
            return HabitContentDTO.fromEntity((HabitContent) cc);
        }else{
            return GoalContentDTO.fromEntity((GoalContent) cc);
        }
    }

}
