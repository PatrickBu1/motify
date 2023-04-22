package com.cs4523groupb11.Motify.entities.abstraction;

import jakarta.persistence.*;

@Entity
@Table(name = "challenge_workload")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ChallengeWorkload {
    private String id;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String getId(){return id;}
    public void setId(String id){this.id = id;}
}
