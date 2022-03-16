package edu.miu.cs544.sujan.service;

import edu.miu.cs544.sujan.entity.Application;

import java.util.List;

public interface ApplicationService {
    Application getApplicationById(Long id);

    List<Application> getApplications();

    Application createApplication(Application application);

    String deleteApplication(Long id);

    Application updateApplication(Long id, Application application);
}
