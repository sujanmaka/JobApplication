package edu.miu.cs544.sujan.controller;

import edu.miu.cs544.sujan.entity.ScreeningInterview;
import edu.miu.cs544.sujan.service.ScreeningInterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/screeninginterviews")
public class ScreeningInterviewController {

    private ScreeningInterviewService screeningInterviewService;

    @Autowired
    public void setScreeningInterviewService(ScreeningInterviewService screeningInterviewService) {
        this.screeningInterviewService = screeningInterviewService;
    }

    @GetMapping
    public List<ScreeningInterview> getScreeningInterviews() {
        return screeningInterviewService.getScreeningInterviews();
    }

    @GetMapping("/{id}")
    public ScreeningInterview getScreeningInterviewById(@PathVariable Long id) {
        return screeningInterviewService.getScreeningInterviewById(id);
    }

    @PostMapping
    public ScreeningInterview createScreeningInterview(@RequestBody ScreeningInterview screeningInterview) {
        return screeningInterviewService.createScreeningInterview(screeningInterview);
    }

    @PutMapping("{id}")
    public ScreeningInterview updateScreeningInterview(@PathVariable Long id, @RequestBody ScreeningInterview screeningInterview) {
        return screeningInterviewService.updateScreeningInterview(id, screeningInterview);
    }

    @DeleteMapping("{id}")
    public String deleteScreeningInterview(@PathVariable Long id) {
       return screeningInterviewService.deleteScreeningInterview(id);
    }
}
