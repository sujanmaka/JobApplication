package edu.miu.cs544.sujan.controller;

import edu.miu.cs544.sujan.entity.TechnicalInterview;
import edu.miu.cs544.sujan.service.TechnicalInterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technicalInterviews")
public class TechnicalInterviewController {

    private TechnicalInterviewService technicalInterviewService;

    @Autowired
    public void setTechnicalInterviewService(TechnicalInterviewService technicalInterviewService) {
        this.technicalInterviewService = technicalInterviewService;
    }

    @GetMapping
    public List<TechnicalInterview> getTechnicalInterviews() {
        return technicalInterviewService.getTechnicalInterviews();
    }

    @GetMapping("/{id}")
    public TechnicalInterview getTechnicalInterviewById(@PathVariable Long id) {
        return technicalInterviewService.getTechnicalInterviewById(id);
    }

    @PostMapping
    public TechnicalInterview createTechnicalInterview(@RequestBody TechnicalInterview technicalInterview) {
        return technicalInterviewService.createTechnicalInterview(technicalInterview);
    }

    @PutMapping("{id}")
    public TechnicalInterview updateTechnicalInterview(@PathVariable Long id, @RequestBody TechnicalInterview technicalInterview) {
        return technicalInterviewService.updateTechnicalInterview(id, technicalInterview);
    }

    @DeleteMapping("{id}")
    public String deleteTechnicalInterview(@PathVariable Long id) {
        return technicalInterviewService.deleteTechnicalInterview(id);
    }
}
