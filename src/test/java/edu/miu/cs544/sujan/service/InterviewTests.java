package edu.miu.cs544.sujan.service;


import edu.miu.cs544.sujan.entity.Interview;
import edu.miu.cs544.sujan.repository.InterviewRepository;
import edu.miu.cs544.sujan.service.impl.InterviewServiceImpl;
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
public class InterviewTests {
    @Mock
    InterviewRepository interviewRepository;

    @InjectMocks
    InterviewServiceImpl interviewService;

    @Test
    public void testGetInterviews() {
        List<Interview> interviews = new ArrayList<>();
        interviews.add(new Interview(LocalDate.now(), "651123456", "abc@miu.edu"));
        Mockito.when(interviewRepository.findAll()).thenReturn(interviews);
        List<Interview> expected = interviewService.getInterviews();
        assertEquals(expected, interviews);
    }

    @Test
    public void testGetInterviewById() {
        Interview interview = new Interview(LocalDate.now(), "651123456", "abc@miu.edu");
        Mockito.when(interviewRepository.findById(1L)).thenReturn(Optional.of(interview));
        Interview expected = interviewService.getInterviewById(1L);
        assertEquals(expected, interview);
    }

    @Test
    public void testCreateInterview() {
        Interview interview = new Interview(LocalDate.now(), "651123456", "abc@miu.edu");
        Mockito.when(interviewRepository.save(interview)).thenReturn(interview);
        Interview createdInterview = interviewService.createInterview(interview);
        assertEquals(createdInterview.getDate(), interview.getDate());
    }

    @Test
    public void testDeleteInterview() {
        Interview interview = new Interview(LocalDate.now(), "651123456", "abc@miu.edu");
        Mockito.when(interviewRepository.findById(1L)).thenReturn(Optional.of(interview));
        String message = interviewService.deleteInterview(1L);
        assertEquals("Interview with id 1 deleted successfully", message);
    }

    @Test
    public void testUpdateInterview() {
        Interview interview = new Interview(LocalDate.now(), "651123456", "abc@miu.edu");
        Mockito.when(interviewRepository.findById(1L)).thenReturn(Optional.of(interview));
        Mockito.when(interviewRepository.save(interview)).thenReturn(interview);
        Interview updatedInterview = interviewService.updateInterview(1L, interview);
        assertEquals(interview, updatedInterview);
    }
}
