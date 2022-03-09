package edu.miu.cs544.sujan.jobboot.controller;

import edu.miu.cs544.sujan.jobboot.entity.Job;
import edu.miu.cs544.sujan.jobboot.service.JobService;
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

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable int id) {
        return jobService.getJobById(id);
    }

    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }

    @PutMapping("{id}")
    public Job updateJob(@PathVariable int id, @RequestBody Job job) {
        return jobService.updateJob(id, job);
    }

    @DeleteMapping("{id}")
    public String deleteJob(@PathVariable int id) {
        jobService.deleteJob(id);
        return "Job deleted successfully";
    }
}
