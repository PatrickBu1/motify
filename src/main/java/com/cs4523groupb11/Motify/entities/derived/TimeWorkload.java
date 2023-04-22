package com.cs4523groupb11.Motify.entities.derived;

import com.cs4523groupb11.Motify.entities.abstraction.ChallengeWorkload;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.Period;

@Entity
@PrimaryKeyJoinColumn(name = "time_workload_id")
public class TimeWorkload extends ChallengeWorkload {
    private Duration duration;

    public TimeWorkload(){}

    public TimeWorkload(Duration duration){
        this.duration = duration;
    }

    @Column(name="duration", nullable = false)
    public Duration getDuration() {return duration;}
    public void setDuration(Duration duration) { this.duration = duration;}
}
