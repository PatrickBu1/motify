package com.cs4523groupb11.Motify.entities.derived;

import com.cs4523groupb11.Motify.entities.abstraction.ChallengeWorkload;
import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "quantity_workload_id")
public class QuantityWorkload extends ChallengeWorkload {
    //private String quantity_id;

    private String unit;

    public QuantityWorkload(){}

    public QuantityWorkload(Integer amount, String unit){
        this.amount = amount;
        this.unit = unit;
    }

    @Column(name="unit", nullable = false)
    public String getUnit() {return unit;}
    public void setUnit(String unit) { this.unit = unit;}
}
