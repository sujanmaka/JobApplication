package edu.miu.cs544.sujan.repository;

import edu.miu.cs544.sujan.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
}
