package edu.miu.cs544.sujan.jobboot.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private int version;

    private LocalDate date;
    private String resumeVersion;

    @OneToOne(cascade = CascadeType.PERSIST, targetEntity = Job.class)
    private Job job;

    public Application() {
    }

    public Application(LocalDate date, String resumeVersion, Job job) {
        this.date = date;
        this.resumeVersion = resumeVersion;
        this.job = job;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", date=" + date +
                ", resumeVersion='" + resumeVersion + '\'' +
                ", job=" + job +
                '}';
    }
}