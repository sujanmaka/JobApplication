package edu.miu.cs544.sujan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cs544.sujan.entity.Question;
import edu.miu.cs544.sujan.service.QuestionService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(QuestionController.class)
class QuestionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetQuestions() throws Exception {
        List<Question> questions = new ArrayList<>(List.of(new Question("What is your name?"), new Question("What is your qualification?")));

        Mockito.when(questionService.getQuestions()).thenReturn(questions);

        mockMvc.perform(MockMvcRequestBuilders.get("/questions").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].quest", is("What is your name?")));
    }

    @Test
    public void testCreateQuestion() throws Exception {
        Question question = new Question("What is your name?");

        Mockito.when(questionService.createQuestion(ArgumentMatchers.any())).thenReturn(question);
        String json = mapper.writeValueAsString(question);
        mockMvc.perform(post("/questions").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.quest", Matchers.equalTo("What is your name?")));

    }

    @Test
    public void testUpdateQuestion() throws Exception {
        Question question = new Question("What is your name?");

        Mockito.when(questionService.updateQuestion(ArgumentMatchers.anyLong(), ArgumentMatchers.any())).thenReturn(question);
        String json = mapper.writeValueAsString(question);
        mockMvc.perform(put("/questions/1").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.quest", is("What is your name?")));
    }

    @Test
    public void testDeleteQuestion() throws Exception {
        Mockito.when(questionService.deleteQuestion(1L)).thenReturn("Question with id 1 deleted successfully");
        MvcResult requestResult = mockMvc.perform(delete("/questions/1")).andExpect(status().isOk()).andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertEquals(result, "Question with id 1 deleted successfully");
    }
}

