package edu.miu.cs544.sujan.repository;

import edu.miu.cs544.sujan.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
