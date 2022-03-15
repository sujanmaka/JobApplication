package edu.miu.cs544.sujan.component;

import edu.miu.cs544.sujan.entity.Job;
import edu.miu.cs544.sujan.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @Autowired
    private JobRepository jobRepository;

    @JmsListener(destination = "${jms.brokerQueue}")
    public void createJob(Job job) {
        jobRepository.save(job);
    }
}
