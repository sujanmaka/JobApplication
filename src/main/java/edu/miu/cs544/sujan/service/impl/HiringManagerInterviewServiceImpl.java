package edu.miu.cs544.sujan.service.impl;

import edu.miu.cs544.sujan.entity.HiringManagerInterview;
import edu.miu.cs544.sujan.exception.DataNotFoundException;
import edu.miu.cs544.sujan.exception.ReferentialIntegrityException;
import edu.miu.cs544.sujan.repository.HiringManagerInterviewRepository;
import edu.miu.cs544.sujan.service.HiringManagerInterviewService;
import edu.miu.cs544.sujan.util.CustomNullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HiringManagerInterviewServiceImpl implements HiringManagerInterviewService {

    private HiringManagerInterviewRepository hiringManagerInterviewRepository;

    @Autowired
    public void setHiringManagerInterviewRepository(HiringManagerInterviewRepository hiringManagerInterviewRepository) {
        this.hiringManagerInterviewRepository = hiringManagerInterviewRepository;
    }

    @Override
    public HiringManagerInterview getHiringManagerInterviewById(Long id) {
        Optional<HiringManagerInterview> hiringManagerInterview = hiringManagerInterviewRepository.findById(id);
        if (hiringManagerInterview.isPresent()) {
            return hiringManagerInterview.get();
        } else {
            throw new DataNotFoundException(String.format("HiringManagerInterview with id %d not found.", id));
        }
    }

    @Override
    public List<HiringManagerInterview> getHiringManagerInterviews() {
        return hiringManagerInterviewRepository.findAll();
    }

    @Override
    public HiringManagerInterview createHiringManagerInterview(HiringManagerInterview hiringManagerInterview) {
        return hiringManagerInterviewRepository.save(hiringManagerInterview);
    }

    @Override
    public String deleteHiringManagerInterview(Long id) {
        try {
            hiringManagerInterviewRepository.delete(getHiringManagerInterviewById(id));
            return String.format("HiringManagerInterview with id %d deleted successfully", id);
        } catch (Exception exception) {
            throw new ReferentialIntegrityException(String.format("HiringManagerInterview with id %d is referenced by other entity.", id));
        }
    }

    @Override
    public HiringManagerInterview updateHiringManagerInterview(Long id, HiringManagerInterview hiringManagerInterview) {
        HiringManagerInterview currentHiringManagerInterview = getHiringManagerInterviewById(id);
        CustomNullAwareBeanUtils.myCopyProperties(hiringManagerInterview, currentHiringManagerInterview);
        return hiringManagerInterviewRepository.save(currentHiringManagerInterview);
    }
}
