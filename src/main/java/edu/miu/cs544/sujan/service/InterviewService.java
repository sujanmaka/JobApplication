package edu.miu.cs544.sujan.service;

import edu.miu.cs544.sujan.entity.Interview;

import java.util.List;

public interface InterviewService {
    Interview getInterviewById(Long id);

    List<Interview> getInterviews();

    Interview createInterview(Interview interview);

    String deleteInterview(Long id);

    Interview updateInterview(Long id, Interview interview);
}
