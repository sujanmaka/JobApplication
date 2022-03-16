package edu.miu.cs544.sujan.service;

import edu.miu.cs544.sujan.entity.HiringManagerInterview;

import java.util.List;

public interface HiringManagerInterviewService {
    HiringManagerInterview getHiringManagerInterviewById(Long id);

    List<HiringManagerInterview> getHiringManagerInterviews();

    HiringManagerInterview createHiringManagerInterview(HiringManagerInterview hiringManagerInterview);

    String deleteHiringManagerInterview(Long id);

    HiringManagerInterview updateHiringManagerInterview(Long id, HiringManagerInterview hiringManagerInterview);
}
