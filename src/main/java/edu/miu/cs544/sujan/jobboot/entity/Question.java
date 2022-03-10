package edu.miu.cs544.sujan.jobboot.entity;

import javax.persistence.*;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private int version;

    private String quest;

    public Question() {
    }

    public Question(String quest) {
        this.quest = quest;
    }
}