package com.cs4523groupb11.Motify.entities;


import com.cs4523groupb11.Motify.entities.abstraction.ChallengeContent;
import com.cs4523groupb11.Motify.entities.enums.ChallengeCategory;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="challenge",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"owner", "name"})})
public class Challenge {
    private String id;

    private User owner;

    private String name;

    private String description;

    private Boolean isPrivate;

    private ChallengeCategory category;

    private ChallengeContent content;

    private Date createdAt;

    public Challenge(){}

    public Challenge(User owner, String name, String description, Boolean isPrivate,
                     ChallengeCategory category, ChallengeContent content, Date createdAt){
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.isPrivate = isPrivate;
        this.category = category;
        this.content = content;
        this.createdAt = createdAt;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String getId(){return id;}
    public void setId(String id){this.id = id;}

    @ManyToOne()
    @JoinColumn(name = "owner", nullable = false)
    public User getOwner(){return owner;}
    public void setOwner(User owner){this.owner = owner;}

    @Column(name="name", nullable = false)
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    @Column(name="description", nullable = false)
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    @Column(name="is_private", nullable = false)
    public Boolean getIsPrivate() { return isPrivate;};
    public void setIsPrivate(Boolean isPrivate){this.isPrivate = isPrivate;}

    @Column(name="category", nullable = false)
    public ChallengeCategory getCategory(){return category;}
    public void setCategory(ChallengeCategory category){this.category = category;}

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "content")
    public ChallengeContent getContent() {return content;}
    public void setContent(ChallengeContent content){this.content = content;}

    @Column(name = "created_at", nullable = false)
    public Date getCreatedAt() { return createdAt;}
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt;}

}
