package edu.miu.cs544.sujan.service;


import edu.miu.cs544.sujan.entity.Application;
import edu.miu.cs544.sujan.entity.Job;
import edu.miu.cs544.sujan.repository.ApplicationRepository;
import edu.miu.cs544.sujan.service.impl.ApplicationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.jgroups.util.Util.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTests {
    @Mock
    ApplicationRepository applicationRepository;

    @InjectMocks
    ApplicationServiceImpl applicationService;

    @Test
    public void testGetApplications() {
        List<Application> applications = new ArrayList<>();
        applications.add(new Application(LocalDate.now(), "1", new Job("Spring Developer", 40000.0)));
        Mockito.when(applicationRepository.findAll()).thenReturn(applications);
        List<Application> expected = applicationService.getApplications();
        assertEquals(expected, applications);
    }

    @Test
    public void testGetApplicationById() {
        Application application = new Application(LocalDate.now(), "1", new Job("Spring Developer", 40000.0));
        Mockito.when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));
        Application expected = applicationService.getApplicationById(1L);
        assertEquals(expected, application);
    }

    @Test
    public void testCreateApplication() {
        Application application = new Application(LocalDate.now(), "1", new Job("Spring Developer", 40000.0));
        Mockito.when(applicationRepository.save(application)).thenReturn(application);
        Application createdApplication = applicationService.createApplication(application);
        assertEquals(createdApplication.getDate(), application.getDate());
    }

    @Test
    public void testDeleteApplication() {
        Application application = new Application(LocalDate.now(), "1", new Job("Spring Developer", 40000.0));
        Mockito.when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));
        String message = applicationService.deleteApplication(1L);
        assertEquals("Application with id 1 deleted successfully", message);
    }

    @Test
    public void testUpdateApplication() {
        Application application = new Application(LocalDate.now(), "1", new Job("Spring Developer", 40000.0));
        Mockito.when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));
        Mockito.when(applicationRepository.save(application)).thenReturn(application);
        Application updatedApplication = applicationService.updateApplication(1L, application);
        assertEquals(application, updatedApplication);
    }
}
