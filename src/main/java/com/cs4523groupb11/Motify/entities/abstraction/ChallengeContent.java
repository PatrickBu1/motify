package com.cs4523groupb11.Motify.entities.abstraction;

import jakarta.persistence.*;

@Entity
@Table(name="challenge_content")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ChallengeContent {
    private String id;

    protected ChallengeWorkload workload;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String getId(){return id;}
    public void setId(String id){this.id = id;}


    @OneToOne(mappedBy = "challenge_content")
    public ChallengeWorkload getWorkload() {return workload;}
    public void setWorkload(ChallengeWorkload workload) {
        this.workload = workload;
    }

}
