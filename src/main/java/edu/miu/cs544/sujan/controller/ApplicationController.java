package edu.miu.cs544.sujan.controller;

import edu.miu.cs544.sujan.entity.Application;
import edu.miu.cs544.sujan.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private ApplicationService applicationService;

    @Autowired
    public void setApplicationService(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public List<Application> getApplications() {
        return applicationService.getApplications();
    }

    @GetMapping("/{id}")
    public Application getApplicationById(@PathVariable Long id) {
        return applicationService.getApplicationById(id);
    }

    @PostMapping
    public Application createApplication(@RequestBody Application application) {
        return applicationService.createApplication(application);
    }

    @PutMapping("{id}")
    public Application updateApplication(@PathVariable Long id, @RequestBody Application application) {
        return applicationService.updateApplication(id, application);
    }

    @DeleteMapping("{id}")
    public String deleteApplication(@PathVariable Long id) {
       return applicationService.deleteApplication(id);
    }
}
