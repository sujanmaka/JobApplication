package edu.miu.cs544.sujan.service.impl;

//import edu.miu.cs544.sujan.jobboot.component.Sender;

import edu.miu.cs544.sujan.component.Sender;
import edu.miu.cs544.sujan.entity.Job;
import edu.miu.cs544.sujan.exception.DataNotFoundException;
import edu.miu.cs544.sujan.exception.ReferentialIntegrityException;
import edu.miu.cs544.sujan.repository.JobRepository;
import edu.miu.cs544.sujan.repository.specification.JobSpecification;
import edu.miu.cs544.sujan.service.JobService;
import edu.miu.cs544.sujan.util.CustomNullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;

    @Autowired
    private Sender sender;

    @Autowired
    public void setJobRepository(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job getJobById(Long id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            return job.get();
        } else {
            throw new DataNotFoundException(String.format("Job with id %d not found.", id));
        }
    }

    @Override
    public List<Job> getJobs() {
        return jobRepository.findAll();
    }

    @Override
    public List<Job> getJobsWithApplication() {
        return jobRepository.getJobsWithApplication();
    }

    @Override
    public List<Job> getJobsWithCompaniesInCertainState(String state) {
        return jobRepository.findJobsWithCompaniesInCertainState(state);
    }

    @Override
    public List<Job> getJobsWithAtLeastTwoInterviews() {
        return jobRepository.findJobsWithAtLeastTwoInterviews();
    }

    @Override
    public List<Job> getJobsWithCertainSalaryAndCompanyInCertainState(double salary, String state) {
        return jobRepository.findAll(JobSpecification.jobsWithCertainSalaryAndCompanyInCertainState(salary, state));
    }

    @Override
    public Job createJob(Job job) {
        sender.createJob(job);
        return job;
    }

    @Override
    public String deleteJob(Long id) {
        try {
            jobRepository.delete(getJobById(id));
            return String.format("Job with id %d deleted successfully", id);
        } catch (Exception exception) {
            throw new ReferentialIntegrityException(String.format("Job with id %d is referenced by other entity.", id));
        }
    }

    @Override
    public Job updateJob(Long id, Job job) {
        Job currentJob = getJobById(id);
        CustomNullAwareBeanUtils.myCopyProperties(job, currentJob);
        return jobRepository.save(currentJob);
    }
}
