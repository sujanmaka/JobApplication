package edu.miu.cs544.sujan.service;

import edu.miu.cs544.sujan.entity.ScreeningInterview;

import java.util.List;

public interface ScreeningInterviewService {
    ScreeningInterview getScreeningInterviewById(Long id);

    List<ScreeningInterview> getScreeningInterviews();

    ScreeningInterview createScreeningInterview(ScreeningInterview screeningInterview);

    String deleteScreeningInterview(Long id);

    ScreeningInterview updateScreeningInterview(Long id, ScreeningInterview screeningInterview);
}
