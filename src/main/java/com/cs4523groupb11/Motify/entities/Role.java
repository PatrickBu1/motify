package com.cs4523groupb11.Motify.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    private Integer id;
    private RoleEnum name;

    public Role(){}
    public Role(RoleEnum name){
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }


    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    public RoleEnum getName() {
        return name;
    }
    public void setName(RoleEnum name) {
        this.name = name;
    }
}
