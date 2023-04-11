package com.cs4523groupb11.Motify.entities.derived;

import com.cs4523groupb11.Motify.entities.abstraction.ChallengeContent;
import com.cs4523groupb11.Motify.entities.abstraction.ChallengeWorkload;
import com.cs4523groupb11.Motify.entities.enums.TimeUnit;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "habit")
public class HabitContent extends ChallengeContent {

    private String habitId;

    private TimeUnit frequency;

    private Boolean isTimeBound;

    private Date startDate;

    private Date endDate;

    public HabitContent(){}

    public HabitContent(ChallengeWorkload workload, TimeUnit frequency, Boolean isTimeBound,
                        Date startDate, Date endDate){
        this.workload = workload;
        this.frequency = frequency;
        this.isTimeBound = isTimeBound;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Override
    public String getId() {return habitId;}
    @Override
    public void setId(String id) {this.habitId = id;}

    @Column(name = "frequency")
    public TimeUnit getFrequency() {return frequency;}
    public void setFrequency(TimeUnit frequency) {this.frequency = frequency;}


    @Column(name = "is_time_bound")
    public Boolean getTimeBound() {return isTimeBound;}
    public void setTimeBound(Boolean timeBound) {
        isTimeBound = timeBound;
    }

    @Column(name="start_date")
    public Date getStartDate() {return startDate;}
    public void setStartDate(Date startDate) {this.startDate = startDate;}

    @Column(name="end_date")
    public Date getEndDate() {return endDate;}
    public void setEndDate(Date endDate) {this.endDate = endDate;}
}