package edu.miu.cs544.sujan.service;

import edu.miu.cs544.sujan.entity.Recruiter;

import java.util.List;

public interface RecruiterService {
    Recruiter getRecruiterById(Long id);

    List<Recruiter> getRecruiters();

    Recruiter createRecruiter(Recruiter recruiter);

    String deleteRecruiter(Long id);

    Recruiter updateRecruiter(Long id, Recruiter recruiter);
}
