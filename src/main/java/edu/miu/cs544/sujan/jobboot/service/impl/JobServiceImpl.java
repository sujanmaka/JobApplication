package edu.miu.cs544.sujan.jobboot.service.impl;

import edu.miu.cs544.sujan.jobboot.entity.Job;
import edu.miu.cs544.sujan.jobboot.repository.JobRepository;
import edu.miu.cs544.sujan.jobboot.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;

    @Autowired
    public void setJobRepository(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job getJobById(Long id) {
        Optional<Job> job = jobRepository.findById(id);
        return job.orElse(null);
    }

    @Override
    public List<Job> getJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public void deleteJob(Long id) {
        jobRepository.delete(getJobById(id));
    }

    @Override
    public Job updateJob(Long id, Job job) {
        Job currentJob = getJobById(id);
        currentJob.setTitle(job.getTitle());
        currentJob.setSalary(job.getSalary());
        return jobRepository.save(currentJob);
    }
}
