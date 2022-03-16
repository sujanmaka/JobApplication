package edu.miu.cs544.sujan.service.impl;

import edu.miu.cs544.sujan.entity.Skill;
import edu.miu.cs544.sujan.exception.DataNotFoundException;
import edu.miu.cs544.sujan.exception.ReferentialIntegrityException;
import edu.miu.cs544.sujan.repository.SkillRepository;
import edu.miu.cs544.sujan.service.JobService;
import edu.miu.cs544.sujan.service.SkillService;
import edu.miu.cs544.sujan.util.CustomNullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {

    private SkillRepository skillRepository;

    private JobService jobService;

    @Autowired
    public void setSkillRepository(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Autowired
    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    @Override
    public Skill getSkillById(Long id) {
        Optional<Skill> skill = skillRepository.findById(id);
        if (skill.isPresent()) {
            return skill.get();
        } else {
            throw new DataNotFoundException(String.format("Skill with id %d not found.", id));
        }
    }

    @Override
    public List<Skill> getSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public String deleteSkill(Long id) {
        try {
            skillRepository.delete(getSkillById(id));
            return String.format("Skill with id %d deleted successfully", id);
        } catch (Exception exception) {
            throw new ReferentialIntegrityException(String.format("Skill with id %d is referenced by other entity.", id));
        }
    }

    @Override
    public Skill updateSkill(Long id, Skill skill) {
        Skill currentSkill = getSkillById(id);
        CustomNullAwareBeanUtils.myCopyProperties(skill, currentSkill);
        return skillRepository.save(currentSkill);
    }

    @Override
    public List<Skill> getSkillsForCertainJobs(double salary, String state) {
        return jobService.getJobsWithCertainSalaryAndCompanyInCertainState(salary, state).
                stream().flatMap(e -> e.getSkills().stream()).collect(Collectors.toList());
    }
}
