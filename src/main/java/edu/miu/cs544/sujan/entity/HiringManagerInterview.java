package edu.miu.cs544.sujan.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class HiringManagerInterview extends Interview {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long id;
//
//    @Version
//    private int version;

    private int teamSize;
    private LocalDate startDate;

    public HiringManagerInterview() {
        super();
    }

    public HiringManagerInterview(LocalDate date, String phone, String email, int teamSize, LocalDate startDate) {
        super(date, phone, email);
        this.teamSize = teamSize;
        this.startDate = startDate;
    }

//    public Long getId() {
//        return id;
//    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}