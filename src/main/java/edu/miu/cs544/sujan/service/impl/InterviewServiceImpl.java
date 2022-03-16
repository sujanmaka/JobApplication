package edu.miu.cs544.sujan.service.impl;

import edu.miu.cs544.sujan.entity.Interview;
import edu.miu.cs544.sujan.exception.DataNotFoundException;
import edu.miu.cs544.sujan.exception.ReferentialIntegrityException;
import edu.miu.cs544.sujan.repository.InterviewRepository;
import edu.miu.cs544.sujan.service.InterviewService;
import edu.miu.cs544.sujan.util.CustomNullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterviewServiceImpl implements InterviewService {

    private InterviewRepository interviewRepository;

    @Autowired
    public void setInterviewRepository(InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
    }

    @Override
    public Interview getInterviewById(Long id) {
        Optional<Interview> interview = interviewRepository.findById(id);
        if (interview.isPresent()) {
            return interview.get();
        } else {
            throw new DataNotFoundException(String.format("Interview with id %d not found.", id));
        }
    }

    @Override
    public List<Interview> getInterviews() {
        return interviewRepository.findAll();
    }

    @Override
    public Interview createInterview(Interview interview) {
        return interviewRepository.save(interview);
    }

    @Override
    public String deleteInterview(Long id) {
        try {
            interviewRepository.delete(getInterviewById(id));
            return String.format("Interview with id %d deleted successfully", id);
        } catch (Exception exception) {
            throw new ReferentialIntegrityException(String.format("Interview with id %d is referenced by other entity.", id));
        }
    }

    @Override
    public Interview updateInterview(Long id, Interview interview) {
        Interview currentInterview = getInterviewById(id);
        CustomNullAwareBeanUtils.myCopyProperties(interview, currentInterview);
        return interviewRepository.save(currentInterview);
    }
}
