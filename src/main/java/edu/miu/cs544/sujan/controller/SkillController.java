package edu.miu.cs544.sujan.controller;

import edu.miu.cs544.sujan.entity.Skill;
import edu.miu.cs544.sujan.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {

    private SkillService skillService;

    @Autowired
    public void setSkillService(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public List<Skill> getSkills() {
        return skillService.getSkills();
    }

    @GetMapping("/{id}")
    public Skill getSkillById(@PathVariable Long id) {
        return skillService.getSkillById(id);
    }

    @PostMapping
    public Skill createSkill(@RequestBody Skill skill) {
        return skillService.createSkill(skill);
    }

    @PutMapping("{id}")
    public Skill updateSkill(@PathVariable Long id, @RequestBody Skill skill) {
        return skillService.updateSkill(id, skill);
    }

    @DeleteMapping("{id}")
    public String deleteSkill(@PathVariable Long id) {
       return skillService.deleteSkill(id);
    }
}
