package edu.miu.cs544.sujan.repository;

import edu.miu.cs544.sujan.entity.TechnicalInterview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicalInterviewRepository extends JpaRepository<TechnicalInterview, Long> {
}
