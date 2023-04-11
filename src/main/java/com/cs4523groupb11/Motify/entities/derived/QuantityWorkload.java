package com.cs4523groupb11.Motify.entities.derived;

import com.cs4523groupb11.Motify.entities.abstraction.ChallengeWorkload;
import jakarta.persistence.*;

@Entity
@Table(name = "quantity_workload")
public class QuantityWorkload extends ChallengeWorkload {
    private String id;

    private String unit;

    public QuantityWorkload(){}

    public QuantityWorkload(Integer amount, String unit){
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
    public String getUnit() {return unit;}
    public void setUnit(String unit) { this.unit = unit;}
}
