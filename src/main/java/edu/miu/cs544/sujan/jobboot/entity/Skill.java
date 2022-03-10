package edu.miu.cs544.sujan.jobboot.entity;

import javax.persistence.*;

@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private int version;

    private String name;
    private String experience;
    private String description;
    private String language;

    public Skill() {
    }

    public Skill(String name, String experience, String description, String language) {
        this.name = name;
        this.experience = experience;
        this.description = description;
        this.language = language;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", experience='" + experience + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}