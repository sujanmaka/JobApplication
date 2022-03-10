package edu.miu.cs544.sujan.jobboot.repository;

import edu.miu.cs544.sujan.jobboot.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
