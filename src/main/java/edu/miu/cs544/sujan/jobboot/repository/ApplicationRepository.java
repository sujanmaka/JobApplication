package edu.miu.cs544.sujan.jobboot.repository;

import edu.miu.cs544.sujan.jobboot.entity.Application;
import edu.miu.cs544.sujan.jobboot.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
