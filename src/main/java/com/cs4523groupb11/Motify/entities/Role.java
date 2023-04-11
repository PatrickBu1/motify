package com.cs4523groupb11.Motify.entities;
import com.cs4523groupb11.Motify.entities.enums.RoleType;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    private Integer id;
    private RoleType name;

    public Role(){}
    public Role(RoleType name){
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
    public RoleType getName() {
        return name;
    }
    public void setName(RoleType name) {
        this.name = name;
    }
}
