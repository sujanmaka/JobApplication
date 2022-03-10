package edu.miu.cs544.sujan.jobboot.entity;

import edu.miu.cs544.sujan.jobboot.enums.Location;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class TechnicalInterview extends Interview {
    @Id
    @GeneratedValue
    private Long id;

    @Version
    private int version;

    private int duration;
    @Enumerated(EnumType.STRING)
    private Location location;
    @OneToMany(cascade = CascadeType.PERSIST, targetEntity = Question.class)
    private List<Question> questions;

    public TechnicalInterview() {
        super();
    }

    public TechnicalInterview(LocalDate date, String phone, String email, int duration, Location location, List<Question> questions) {
        super(date, phone, email);
        this.duration = duration;
        this.location = location;
        this.questions = questions;
    }
}