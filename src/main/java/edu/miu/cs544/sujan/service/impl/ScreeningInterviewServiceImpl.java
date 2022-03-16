package edu.miu.cs544.sujan.service.impl;

import edu.miu.cs544.sujan.entity.ScreeningInterview;
import edu.miu.cs544.sujan.exception.DataNotFoundException;
import edu.miu.cs544.sujan.exception.ReferentialIntegrityException;
import edu.miu.cs544.sujan.repository.ScreeningInterviewRepository;
import edu.miu.cs544.sujan.service.ScreeningInterviewService;
import edu.miu.cs544.sujan.util.CustomNullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScreeningInterviewServiceImpl implements ScreeningInterviewService {

    private ScreeningInterviewRepository screeningInterviewRepository;

    @Autowired
    public void setScreeningInterviewRepository(ScreeningInterviewRepository screeningInterviewRepository) {
        this.screeningInterviewRepository = screeningInterviewRepository;
    }

    @Override
    public ScreeningInterview getScreeningInterviewById(Long id) {
        Optional<ScreeningInterview> screeningInterview = screeningInterviewRepository.findById(id);
        if (screeningInterview.isPresent()) {
            return screeningInterview.get();
        } else {
            throw new DataNotFoundException(String.format("ScreeningInterview with id %d not found.", id));
        }
    }

    @Override
    public List<ScreeningInterview> getScreeningInterviews() {
        return screeningInterviewRepository.findAll();
    }

    @Override
    public ScreeningInterview createScreeningInterview(ScreeningInterview screeningInterview) {
        return screeningInterviewRepository.save(screeningInterview);
    }

    @Override
    public String deleteScreeningInterview(Long id) {
        try {
            screeningInterviewRepository.delete(getScreeningInterviewById(id));
            return String.format("ScreeningInterview with id %d deleted successfully", id);
        } catch (Exception exception) {
            throw new ReferentialIntegrityException(String.format("ScreeningInterview with id %d is referenced by other entity.", id));
        }
    }

    @Override
    public ScreeningInterview updateScreeningInterview(Long id, ScreeningInterview screeningInterview) {
        ScreeningInterview currentScreeningInterview = getScreeningInterviewById(id);
        CustomNullAwareBeanUtils.myCopyProperties(screeningInterview, currentScreeningInterview);
        return screeningInterviewRepository.save(currentScreeningInterview);
    }
}
