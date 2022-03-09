package edu.miu.cs544.sujan.jobboot.service;

import edu.miu.cs544.sujan.jobboot.entity.Job;

import java.util.List;

public interface JobService {
    Job getJobById(int id);

    List<Job> getJobs();

    Job createJob(Job job);

    void deleteJob(int id);

    Job updateJob(int id, Job job);
}
