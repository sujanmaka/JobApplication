package edu.miu.cs544.sujan.service.impl;

import edu.miu.cs544.sujan.entity.Question;
import edu.miu.cs544.sujan.exception.DataNotFoundException;
import edu.miu.cs544.sujan.exception.ReferentialIntegrityException;
import edu.miu.cs544.sujan.repository.QuestionRepository;
import edu.miu.cs544.sujan.service.QuestionService;
import edu.miu.cs544.sujan.util.CustomNullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;

    @Autowired
    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question getQuestionById(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException(String.format("Question with id %d not found.", id));
        }
    }

    @Override
    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public String deleteQuestion(Long id) {
        try {
            questionRepository.delete(getQuestionById(id));
            return String.format("Question with id %d deleted successfully", id);
        } catch (Exception exception) {
            throw new ReferentialIntegrityException(String.format("Question with id %d is referenced by other entity.", id));
        }
    }

    @Override
    public Question updateQuestion(Long id, Question question) {
        Question currentQuestion = getQuestionById(id);
        CustomNullAwareBeanUtils.myCopyProperties(question, currentQuestion);
        return questionRepository.save(currentQuestion);
    }
}
