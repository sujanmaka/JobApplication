package edu.miu.cs544.sujan.repository;

import edu.miu.cs544.sujan.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
