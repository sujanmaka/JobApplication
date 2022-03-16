package edu.miu.cs544.sujan.service.impl;

import edu.miu.cs544.sujan.entity.TechnicalInterview;
import edu.miu.cs544.sujan.exception.DataNotFoundException;
import edu.miu.cs544.sujan.exception.ReferentialIntegrityException;
import edu.miu.cs544.sujan.repository.TechnicalInterviewRepository;
import edu.miu.cs544.sujan.service.TechnicalInterviewService;
import edu.miu.cs544.sujan.util.CustomNullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicalInterviewServiceImpl implements TechnicalInterviewService {

    private TechnicalInterviewRepository technicalInterviewRepository;

    @Autowired
    public void setTechnicalInterviewRepository(TechnicalInterviewRepository technicalInterviewRepository) {
        this.technicalInterviewRepository = technicalInterviewRepository;
    }

    @Override
    public TechnicalInterview getTechnicalInterviewById(Long id) {
        Optional<TechnicalInterview> technicalInterview = technicalInterviewRepository.findById(id);
        if (technicalInterview.isPresent()) {
            return technicalInterview.get();
        } else {
            throw new DataNotFoundException(String.format("TechnicalInterview with id %d not found.", id));
        }
    }

    @Override
    public List<TechnicalInterview> getTechnicalInterviews() {
        return technicalInterviewRepository.findAll();
    }

    @Override
    public TechnicalInterview createTechnicalInterview(TechnicalInterview technicalInterview) {
        return technicalInterviewRepository.save(technicalInterview);
    }

    @Override
    public String deleteTechnicalInterview(Long id) {
        try {
            technicalInterviewRepository.delete(getTechnicalInterviewById(id));
            return String.format("TechnicalInterview with id %d deleted successfully", id);
        } catch (Exception exception) {
            throw new ReferentialIntegrityException(String.format("TechnicalInterview with id %d is referenced by other entity.", id));
        }
    }

    @Override
    public TechnicalInterview updateTechnicalInterview(Long id, TechnicalInterview technicalInterview) {
        TechnicalInterview currentTechnicalInterview = getTechnicalInterviewById(id);
        CustomNullAwareBeanUtils.myCopyProperties(technicalInterview, currentTechnicalInterview);
        return technicalInterviewRepository.save(currentTechnicalInterview);
    }
}
