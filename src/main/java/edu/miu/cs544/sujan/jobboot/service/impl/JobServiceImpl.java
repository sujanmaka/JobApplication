package edu.miu.cs544.sujan.jobboot.service.impl;

import edu.miu.cs544.sujan.jobboot.entity.Job;
import edu.miu.cs544.sujan.jobboot.service.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    List<Job> jobs = new ArrayList<>();

    public JobServiceImpl() {
        jobs.add(new Job("Software Developer", 30000));
        jobs.add(new Job("Software Tester", 20000));
        jobs.add(new Job("Product Manager", 50000));
        jobs.add(new Job("Software Architect", 40000));
    }

    @Override
    public Job getJobById(int id) {
        for (Job job : jobs) {
            if (job.getId() == id) {
                return job;
            }
        }
        return null;
    }

    @Override
    public List<Job> getJobs() {
        return jobs;
    }

    @Override
    public Job createJob(Job job) {
        jobs.add(job);
        return job;
    }

    @Override
    public void deleteJob(int id) {
        for (Job job : jobs) {
            if (job.getId() == id) {
                jobs.remove(job);
                break;
            }
        }
    }

    @Override
    public Job updateJob(int id, Job job) {
        for (Job existingJob : jobs) {
            if (existingJob.getId() == id) {
                existingJob.setSalary(job.getSalary());
                existingJob.setTitle(job.getTitle());
                return existingJob;
            }
        }
        return null;
    }
}
