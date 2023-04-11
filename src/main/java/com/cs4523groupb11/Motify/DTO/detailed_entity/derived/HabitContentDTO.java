package com.cs4523groupb11.Motify.DTO.detailed_entity.derived;


import com.cs4523groupb11.Motify.DTO.detailed_entity.abstraction.ChallengeContentDTO;
import com.cs4523groupb11.Motify.DTO.detailed_entity.abstraction.ChallengeWorkloadDTO;
import com.cs4523groupb11.Motify.entities.abstraction.ChallengeWorkload;
import com.cs4523groupb11.Motify.entities.derived.HabitContent;
import com.cs4523groupb11.Motify.entities.derived.QuantityWorkload;
import com.cs4523groupb11.Motify.entities.derived.TimeWorkload;

import java.util.Date;

public class HabitContentDTO extends ChallengeContentDTO {

    private String frequency;

    private Boolean isTimeBound;

    private Date startDate;

    private Date endDate;

    public HabitContentDTO(){}

    public HabitContentDTO(ChallengeWorkloadDTO workload, String frequency, Boolean isTimeBound,
                           Date startDate, Date endDate){
        this.workload = workload;
        this.frequency = frequency;
        this.isTimeBound = isTimeBound;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getFrequency() {return frequency;}
    public void setFrequency(String frequency) {this.frequency = frequency;}

    public Boolean getTimeBound() {return isTimeBound;}
    public void setTimeBound(Boolean timeBound) {
        isTimeBound = timeBound;
    }

    public Date getStartDate() {return startDate;}
    public void setStartDate(Date startDate) {this.startDate = startDate;}

    public Date getEndDate() {return endDate;}
    public void setEndDate(Date endDate) {this.endDate = endDate;}

    public static HabitContentDTO fromEntity(HabitContent hc){
        ChallengeWorkloadDTO wlDTO = ChallengeWorkloadDTO.fromEntity(hc.getWorkload());
        return new HabitContentDTO(wlDTO, hc.getFrequency().toString(), hc.getTimeBound(),
                hc.getStartDate(), hc.getEndDate());
    }
}
