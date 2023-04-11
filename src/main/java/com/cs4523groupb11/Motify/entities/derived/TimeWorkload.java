package com.cs4523groupb11.Motify.entities.derived;

import com.cs4523groupb11.Motify.entities.abstraction.ChallengeWorkload;
import com.cs4523groupb11.Motify.entities.enums.TimeUnit;
import jakarta.persistence.*;

@Entity
@Table(name = "time_workload")
public class TimeWorkload extends ChallengeWorkload {
    private String id;

    private TimeUnit unit;


    public TimeWorkload(){}

    public TimeWorkload(Integer amount, TimeUnit unit){
        this.amount = amount;
        this.unit = unit;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Override
    public String getId(){return id;}
    @Override
    public void setId(String id){this.id = id;}

    @Column(name="unit", nullable = false)
    public TimeUnit getUnit() {return unit;}
    public void setUnit(TimeUnit unit) { this.unit = unit;}
}
