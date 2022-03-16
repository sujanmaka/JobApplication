package edu.miu.cs544.sujan.service.impl;

import edu.miu.cs544.sujan.entity.Recruiter;
import edu.miu.cs544.sujan.exception.DataNotFoundException;
import edu.miu.cs544.sujan.exception.ReferentialIntegrityException;
import edu.miu.cs544.sujan.repository.RecruiterRepository;
import edu.miu.cs544.sujan.service.RecruiterService;
import edu.miu.cs544.sujan.util.CustomNullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecruiterServiceImpl implements RecruiterService {

    private RecruiterRepository recruiterRepository;

    @Autowired
    public void setRecruiterRepository(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }

    @Override
    public Recruiter getRecruiterById(Long id) {
        Optional<Recruiter> recruiter = recruiterRepository.findById(id);
        if (recruiter.isPresent()) {
            return recruiter.get();
        } else {
            throw new DataNotFoundException(String.format("Recruiter with id %d not found.", id));
        }
    }

    @Override
    public List<Recruiter> getRecruiters() {
        return recruiterRepository.findAll();
    }

    @Override
    public Recruiter createRecruiter(Recruiter recruiter) {
        return recruiterRepository.save(recruiter);
    }

    @Override
    public String deleteRecruiter(Long id) {
        try {
            recruiterRepository.delete(getRecruiterById(id));
            return String.format("Recruiter with id %d deleted successfully", id);
        } catch (Exception exception) {
            throw new ReferentialIntegrityException(String.format("Recruiter with id %d is referenced by other entity.", id));
        }
    }

    @Override
    public Recruiter updateRecruiter(Long id, Recruiter recruiter) {
        Recruiter currentRecruiter = getRecruiterById(id);
        CustomNullAwareBeanUtils.myCopyProperties(recruiter, currentRecruiter);
        return recruiterRepository.save(currentRecruiter);
    }
}
