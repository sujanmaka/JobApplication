package edu.miu.cs544.sujan.repository;

import edu.miu.cs544.sujan.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {
    @Query("select r from Recruiter as r inner join Job as j on r.id = j.company.id where j.salary > ?1")
    List<Recruiter> findRecruitersWithJobPayingMoreThan(double salary);
}
