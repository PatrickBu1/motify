package com.cs4523groupb11.Motify.entities.derived;


import com.cs4523groupb11.Motify.entities.abstraction.ChallengeContent;
import com.cs4523groupb11.Motify.entities.abstraction.ChallengeWorkload;
import com.cs4523groupb11.Motify.entities.enums.TimeUnit;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="goal")
public class GoalContent extends ChallengeContent {
    public String goalId;

    public GoalContent(){}

    public GoalContent(ChallengeWorkload workload){
        this.workload = workload;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Override
    public String getId(){
        return goalId;
    }
    @Override
    public void setId(String id){
        this.goalId = id;
    }
}
