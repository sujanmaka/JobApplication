package edu.miu.cs544.sujan.service;

import edu.miu.cs544.sujan.entity.Question;

import java.util.List;

public interface QuestionService {
    Question getQuestionById(Long id);

    List<Question> getQuestions();

    Question createQuestion(Question question);

    String deleteQuestion(Long id);

    Question updateQuestion(Long id, Question question);
}
