package edu.miu.cs544.sujan.controller;

import edu.miu.cs544.sujan.entity.Job;
import edu.miu.cs544.sujan.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private JobService jobService;

    @Autowired
    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<Job> getJobs() {
        return jobService.getJobs();
    }

    @GetMapping("/applications")
    public List<Job> getJobsWithApplication() {
        return jobService.getJobsWithApplication();
    }

    @GetMapping("/companies")
    public List<Job> getJobsWithCompaniesInCertainState(@RequestParam String state) {
        return jobService.getJobsWithCompaniesInCertainState(state);
    }

    @GetMapping("/interviews")
    public List<Job> getJobsWithAtLeastTwoInterviews() {
        return jobService.getJobsWithAtLeastTwoInterviews();
    }

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }

    @PutMapping("{id}")
    public Job updateJob(@PathVariable Long id, @RequestBody Job job) {
        return jobService.updateJob(id, job);
    }

    @DeleteMapping("{id}")
    public String deleteJob(@PathVariable Long id) {
        return jobService.deleteJob(id);
    }
}
