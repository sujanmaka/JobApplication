package edu.miu.cs544.sujan.service;

import edu.miu.cs544.sujan.entity.TechnicalInterview;

import java.util.List;

public interface TechnicalInterviewService {
    TechnicalInterview getTechnicalInterviewById(Long id);

    List<TechnicalInterview> getTechnicalInterviews();

    TechnicalInterview createTechnicalInterview(TechnicalInterview technicalInterview);

    String deleteTechnicalInterview(Long id);

    TechnicalInterview updateTechnicalInterview(Long id, TechnicalInterview technicalInterview);
}
