package edu.miu.cs544.sujan.service;

import edu.miu.cs544.sujan.entity.Skill;

import java.util.List;

public interface SkillService {
    Skill getSkillById(Long id);

    List<Skill> getSkills();

    Skill createSkill(Skill skill);

    String deleteSkill(Long id);

    Skill updateSkill(Long id, Skill skill);

    List<Skill> getSkillsForCertainJobs(double salary, String state);
}
