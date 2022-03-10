package edu.miu.cs544.sujan.jobboot.service;

import edu.miu.cs544.sujan.jobboot.entity.Job;

import java.util.List;

public interface JobService {
    Job getJobById(Long id);

    List<Job> getJobs();

    Job createJob(Job job);

    void deleteJob(Long id);

    Job updateJob(Long id, Job job);
}
