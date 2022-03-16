package edu.miu.cs544.sujan.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class ScreeningInterview extends Interview {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long id;
//
//    @Version
//    private int version;

    private String name;
    private String result;

    public ScreeningInterview() {
        super();
    }

    public ScreeningInterview(LocalDate date, String phone, String email, String name, String result) {
        super(date, phone, email);
        this.name = name;
        this.result = result;
    }

//    public Long getId() {
//        return id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}