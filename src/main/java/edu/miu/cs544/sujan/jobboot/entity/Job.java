package edu.miu.cs544.sujan.jobboot.entity;

public class Job {
    private static int count = 0;
    private int id;
    private String title;
    private double salary;


    public Job(String title, double salary) {
        this.id = count++;
        this.title = title;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
