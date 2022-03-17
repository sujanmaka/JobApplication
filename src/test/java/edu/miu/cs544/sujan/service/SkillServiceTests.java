package edu.miu.cs544.sujan.service;


import edu.miu.cs544.sujan.entity.Skill;
import edu.miu.cs544.sujan.repository.SkillRepository;
import edu.miu.cs544.sujan.service.impl.SkillServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.jgroups.util.Util.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SkillServiceTests {
    @Mock
    SkillRepository skillRepository;

    @InjectMocks
    SkillServiceImpl skillService;

    @Test
    public void testGetSkills() {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("Coding", "4 years", "Smart coder", "Java"));
        Mockito.when(skillRepository.findAll()).thenReturn(skills);
        List<Skill> expected = skillService.getSkills();
        assertEquals(expected, skills);
    }

    @Test
    public void testGetSkillById() {
        Skill skill = new Skill("Coding", "4 years", "Smart coder", "Java");
        Mockito.when(skillRepository.findById(1L)).thenReturn(Optional.of(skill));
        Skill expected = skillService.getSkillById(1L);
        assertEquals(expected, skill);
    }

    @Test
    public void testCreateSkill() {
        Skill skill = new Skill("Coding", "4 years", "Smart coder", "Java");
        Mockito.when(skillRepository.save(skill)).thenReturn(skill);
        Skill createdSkill = skillService.createSkill(skill);
        assertEquals(createdSkill.getName(), skill.getName());
    }

    @Test
    public void testDeleteSkill() {
        Skill skill = new Skill("Coding", "4 years", "Smart coder", "Java");
        Mockito.when(skillRepository.findById(1L)).thenReturn(Optional.of(skill));
        String message = skillService.deleteSkill(1L);
        assertEquals("Skill with id 1 deleted successfully", message);
    }

    @Test
    public void testUpdateSkill() {
        Skill skill = new Skill("Coding", "4 years", "Smart coder", "Java");
        Mockito.when(skillRepository.findById(1L)).thenReturn(Optional.of(skill));
        Mockito.when(skillRepository.save(skill)).thenReturn(skill);
        Skill updatedSkill = skillService.updateSkill(1L, skill);
        assertEquals(skill, updatedSkill);
    }
}
