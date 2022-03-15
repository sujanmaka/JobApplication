package edu.miu.cs544.sujan.repository;

import edu.miu.cs544.sujan.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
