package edu.miu.cs544.sujan.jobboot.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class Interview {
    @Id
    @GeneratedValue
    private Long id;

    @Version
    private int version;

    private LocalDate date;
    private String phone;
    private String email;

    public Interview() {
    }

    public Interview(LocalDate date, String phone, String email) {
        this.date = date;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Interview{" +
                "id=" + id +
                ", date=" + date +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
