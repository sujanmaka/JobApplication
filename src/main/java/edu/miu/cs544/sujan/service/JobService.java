package edu.miu.cs544.sujan.service;

import edu.miu.cs544.sujan.entity.Job;

import java.util.List;

public interface JobService {
    Job getJobById(Long id);

    List<Job> getJobs();

    List<Job> getJobsWithApplication();

    List<Job> getJobsWithCompaniesInCertainState(String state);

    List<Job> getJobsWithAtLeastTwoInterviews();

    Job createJob(Job job);

    String deleteJob(Long id);

    Job updateJob(Long id, Job job);

    List<Job> getJobsWithCertainSalaryAndCompanyInCertainState(double salary, String state);
}
