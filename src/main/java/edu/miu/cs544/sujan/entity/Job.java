package edu.miu.cs544.sujan.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Version
    private int version;

    private String title;
    private double salary;
    @OneToMany(cascade = CascadeType.ALL,targetEntity = Skill.class)
    private List<Skill> skills;
    @ManyToOne(cascade = CascadeType.ALL,targetEntity = Company.class)
    private Company company;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Interview.class)
    private List<Interview> interviews;

    public Job() {
    }

    public Job(String title, double salary) {
        this.title = title;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Interview> getInterviews() {
        return interviews;
    }

    public void setInterviews(List<Interview> interviews) {
        this.interviews = interviews;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", salary=" + salary +
                ", skills=" + skills +
                ", company=" + company +
                ", interviews=" + interviews +
                '}';
    }
}