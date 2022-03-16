package edu.miu.cs544.sujan.controller;

import edu.miu.cs544.sujan.entity.Interview;
import edu.miu.cs544.sujan.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interviews")
public class InterviewController {

    private InterviewService interviewService;

    @Autowired
    public void setInterviewService(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @GetMapping
    public List<Interview> getInterviews() {
        return interviewService.getInterviews();
    }

    @GetMapping("/weeks")
    public List<Interview> getInterviewsWithAWeek() {
        return interviewService.getInterviewsWithAWeek();
    }

    @GetMapping("/{id}")
    public Interview getInterviewById(@PathVariable Long id) {
        return interviewService.getInterviewById(id);
    }

    @PostMapping
    public Interview createInterview(@RequestBody Interview interview) {
        return interviewService.createInterview(interview);
    }

    @PutMapping("{id}")
    public Interview updateInterview(@PathVariable Long id, @RequestBody Interview interview) {
        return interviewService.updateInterview(id, interview);
    }

    @DeleteMapping("{id}")
    public String deleteInterview(@PathVariable Long id) {
       return interviewService.deleteInterview(id);
    }
}
