package edu.miu.cs544.sujan.repository;

import edu.miu.cs544.sujan.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {
    @Query(value = "select j.* FROM application as a inner join job as j on a.job_id=j.id", nativeQuery = true)
    public List<Job> getJobsWithApplication();

    public List<Job> findJobsWithCompaniesInCertainState(String state);

    @Query(value = "select j from Job as j where size(j.interviews) >= 2")
    public List<Job> findJobsWithAtLeastTwoInterviews();
}
