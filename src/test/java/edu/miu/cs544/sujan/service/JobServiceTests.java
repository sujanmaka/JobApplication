package edu.miu.cs544.sujan.service;


import edu.miu.cs544.sujan.component.Sender;
import edu.miu.cs544.sujan.entity.Job;
import edu.miu.cs544.sujan.repository.JobRepository;
import edu.miu.cs544.sujan.service.impl.JobServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.jgroups.util.Util.assertEquals;

@ExtendWith(MockitoExtension.class)
public class JobServiceTests {
    @Mock
    JobRepository jobRepository;

    @InjectMocks
    JobServiceImpl jobService;

    @Mock
    Sender sender;

    @Test
    public void testGetJobs() {
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job("Programmer", 60000));
        Mockito.when(jobRepository.findAll()).thenReturn(jobs);
        List<Job> expected = jobService.getJobs();
        assertEquals(expected, jobs);
    }

    @Test
    public void testGetJobById() {
        Job job = new Job("Programmer", 60000);
        Mockito.when(jobRepository.findById(1L)).thenReturn(Optional.of(job));
        Job expected = jobService.getJobById(1L);
        assertEquals(expected, job);
    }

    @Test
    public void testCreateJob() {
        Job job = new Job("Programmer", 60000);
        Mockito.doNothing().when(sender).createJob(job);
        Job createdJob = jobService.createJob(job);
        assertEquals(createdJob.getTitle(), job.getTitle());
    }

    @Test
    public void testDeleteJob() {
        Job job = new Job("Programmer", 60000);
        Mockito.when(jobRepository.findById(1L)).thenReturn(Optional.of(job));
        String message = jobService.deleteJob(1L);
        assertEquals("Job with id 1 deleted successfully", message);
    }

    @Test
    public void testUpdateJob() {
        Job job = new Job("Programmer", 60000);
        Mockito.when(jobRepository.findById(1L)).thenReturn(Optional.of(job));
        Mockito.when(jobRepository.save(job)).thenReturn(job);
        Job updatedJob = jobService.updateJob(1L, job);
        assertEquals(job, updatedJob);
    }
}
