package com.cs4523groupb11.Motify.entities.derived;


import com.cs4523groupb11.Motify.entities.abstraction.ChallengeContent;
import com.cs4523groupb11.Motify.entities.abstraction.ChallengeWorkload;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "goal_content_id")
public class GoalContent extends ChallengeContent {
    //public String goalId;

    public GoalContent(){}

    public GoalContent(ChallengeWorkload workload){
        this.workload = workload;
    }

}
