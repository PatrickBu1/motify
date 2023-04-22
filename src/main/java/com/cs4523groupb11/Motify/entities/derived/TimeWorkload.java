package com.cs4523groupb11.Motify.entities.derived;

import com.cs4523groupb11.Motify.entities.abstraction.ChallengeWorkload;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.Period;

@Entity
@PrimaryKeyJoinColumn(name = "time_workload_id")
public class TimeWorkload extends ChallengeWorkload {

    private Period period;

    private Duration duration;

    public TimeWorkload(){}

    public TimeWorkload(Period period, Duration duration){
        this.period = period;
        this.duration = duration;
    }

    @Column(name="period", nullable = false)
    public Period getPeriod() {return period;}
    public void setPeriod(Period period) { this.period = period;}

    @Column(name="duration", nullable = false)
    public Duration getDuration() {return duration;}
    public void setDuration(Duration duration) { this.duration = duration;}
}
