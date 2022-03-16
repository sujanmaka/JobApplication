package edu.miu.cs544.sujan.controller;

import edu.miu.cs544.sujan.entity.Recruiter;
import edu.miu.cs544.sujan.service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recruiters")
public class RecruiterController {

    private RecruiterService recruiterService;

    @Autowired
    public void setRecruiterService(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }

    @GetMapping
    public List<Recruiter> getRecruiters() {
        return recruiterService.getRecruiters();
    }

    @GetMapping("/jobs")
    public List<Recruiter> getRecruitersWithJobPayingMoreThan(@RequestParam double salary) {
        return recruiterService.getRecruitersWithJobPayingMoreThan(salary);
    }

    @GetMapping("/{id}")
    public Recruiter getRecruiterById(@PathVariable Long id) {
        return recruiterService.getRecruiterById(id);
    }

    @PostMapping
    public Recruiter createRecruiter(@RequestBody Recruiter recruiter) {
        return recruiterService.createRecruiter(recruiter);
    }

    @PutMapping("{id}")
    public Recruiter updateRecruiter(@PathVariable Long id, @RequestBody Recruiter recruiter) {
        return recruiterService.updateRecruiter(id, recruiter);
    }

    @DeleteMapping("{id}")
    public String deleteRecruiter(@PathVariable Long id) {
       return recruiterService.deleteRecruiter(id);
    }
}
