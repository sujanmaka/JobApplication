package edu.miu.cs544.sujan.repository;

import edu.miu.cs544.sujan.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
    @Query("select i from Interview as i where i.date between ?1 and ?2")
    List<Interview> getInterviewsWithinDate(LocalDate fromDate, LocalDate toDate);
}
