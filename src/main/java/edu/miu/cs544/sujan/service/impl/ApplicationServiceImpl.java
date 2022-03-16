package edu.miu.cs544.sujan.service.impl;

import edu.miu.cs544.sujan.entity.Application;
import edu.miu.cs544.sujan.exception.DataNotFoundException;
import edu.miu.cs544.sujan.exception.ReferentialIntegrityException;
import edu.miu.cs544.sujan.repository.ApplicationRepository;
import edu.miu.cs544.sujan.service.ApplicationService;
import edu.miu.cs544.sujan.util.CustomNullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationRepository applicationRepository;

    @Autowired
    public void setApplicationRepository(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Application getApplicationById(Long id) {
        Optional<Application> application = applicationRepository.findById(id);
        if (application.isPresent()) {
            return application.get();
        } else {
            throw new DataNotFoundException(String.format("Application with id %d not found.", id));
        }
    }

    @Override
    public List<Application> getApplications() {
        return applicationRepository.findAll();
    }

    @Override
    public Application createApplication(Application application) {
        return applicationRepository.save(application);
    }

    @Override
    public String deleteApplication(Long id) {
        try {
            applicationRepository.delete(getApplicationById(id));
            return String.format("Application with id %d deleted successfully", id);
        } catch (Exception exception) {
            throw new ReferentialIntegrityException(String.format("Application with id %d is referenced by other entity.", id));
        }
    }

    @Override
    public Application updateApplication(Long id, Application application) {
        Application currentApplication = getApplicationById(id);
        CustomNullAwareBeanUtils.myCopyProperties(application, currentApplication);
        return applicationRepository.save(currentApplication);
    }
}
