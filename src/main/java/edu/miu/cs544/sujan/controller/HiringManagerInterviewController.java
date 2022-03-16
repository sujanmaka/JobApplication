package edu.miu.cs544.sujan.controller;

import edu.miu.cs544.sujan.entity.HiringManagerInterview;
import edu.miu.cs544.sujan.service.HiringManagerInterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hiringmanagerinterviews")
public class HiringManagerInterviewController {

    private HiringManagerInterviewService hiringManagerInterviewService;

    @Autowired
    public void setHiringManagerInterviewService(HiringManagerInterviewService hiringManagerInterviewService) {
        this.hiringManagerInterviewService = hiringManagerInterviewService;
    }

    @GetMapping
    public List<HiringManagerInterview> getHiringManagerInterviews() {
        return hiringManagerInterviewService.getHiringManagerInterviews();
    }

    @GetMapping("/{id}")
    public HiringManagerInterview getHiringManagerInterviewById(@PathVariable Long id) {
        return hiringManagerInterviewService.getHiringManagerInterviewById(id);
    }

    @PostMapping
    public HiringManagerInterview createHiringManagerInterview(@RequestBody HiringManagerInterview hiringManagerInterview) {
        return hiringManagerInterviewService.createHiringManagerInterview(hiringManagerInterview);
    }

    @PutMapping("{id}")
    public HiringManagerInterview updateHiringManagerInterview(@PathVariable Long id, @RequestBody HiringManagerInterview hiringManagerInterview) {
        return hiringManagerInterviewService.updateHiringManagerInterview(id, hiringManagerInterview);
    }

    @DeleteMapping("{id}")
    public String deleteHiringManagerInterview(@PathVariable Long id) {
       return hiringManagerInterviewService.deleteHiringManagerInterview(id);
    }
}
