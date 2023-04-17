package com.cs4523groupb11.Motify.entities.derived;

import com.cs4523groupb11.Motify.entities.abstraction.ChallengeWorkload;
import com.cs4523groupb11.Motify.entities.enums.TimeUnit;
import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "time_workload_id")
public class TimeWorkload extends ChallengeWorkload {
    //private String id;

    private TimeUnit timeUnit;


    public TimeWorkload(){}

    public TimeWorkload(Integer amount, TimeUnit timeUnit){
        this.amount = amount;
        this.timeUnit = timeUnit;
    }

    @Column(name="time_unit", nullable = false)
    public TimeUnit getTimeUnit() {return timeUnit;}
    public void setTimeUnit(TimeUnit unit) { this.timeUnit = unit;}
}
