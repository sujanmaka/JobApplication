package edu.miu.cs544.sujan.entity;

import javax.persistence.*;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Version
    private int version;

    private String quest;

    public Question() {
    }

    public Question(String quest) {
        this.quest = quest;
    }

    public Long getId() {
        return id;
    }

    public String getQuest() {
        return quest;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }
}