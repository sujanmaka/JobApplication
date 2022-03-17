package edu.miu.cs544.sujan.service;


import edu.miu.cs544.sujan.entity.Question;
import edu.miu.cs544.sujan.repository.QuestionRepository;
import edu.miu.cs544.sujan.service.impl.QuestionServiceImpl;
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
public class QuestionTests {
    @Mock
    QuestionRepository questionRepository;

    @InjectMocks
    QuestionServiceImpl questionService;

    @Test
    public void testGetQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is your name?"));
        Mockito.when(questionRepository.findAll()).thenReturn(questions);
        List<Question> expected = questionService.getQuestions();
        assertEquals(expected, questions);
    }

    @Test
    public void testGetQuestionById() {
        Question question = new Question("What is your name?");
        Mockito.when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        Question expected = questionService.getQuestionById(1L);
        assertEquals(expected, question);
    }

    @Test
    public void testCreateQuestion() {
        Question question = new Question("What is your name?");
        Mockito.when(questionRepository.save(question)).thenReturn(question);
        Question createdQuestion = questionService.createQuestion(question);
        assertEquals(createdQuestion.getQuest(), question.getQuest());
    }

    @Test
    public void testDeleteQuestion() {
        Question question = new Question("What is your name?");
        Mockito.when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        String message = questionService.deleteQuestion(1L);
        assertEquals("Question with id 1 deleted successfully", message);
    }

    @Test
    public void testUpdateQuestion() {
        Question question = new Question("What is your name?");
        Mockito.when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        Mockito.when(questionRepository.save(question)).thenReturn(question);
        Question updatedQuestion = questionService.updateQuestion(1L, question);
        assertEquals(question, updatedQuestion);
    }
}
