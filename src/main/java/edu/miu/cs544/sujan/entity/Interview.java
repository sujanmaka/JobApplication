package edu.miu.cs544.sujan.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Interview implements Serializable {
    private static final long serialVersionUID = 1L;

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

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Interview{" + "id=" + id + ", date=" + date + ", phone='" + phone + '\'' + ", email='" + email + '\'' + '}';
    }
}
