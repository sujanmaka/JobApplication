package edu.miu.cs544.sujan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs544.sujan.entity.Interview;
import edu.miu.cs544.sujan.service.InterviewService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(InterviewController.class)
class InterviewControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InterviewService interviewService;

    private static ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void addPropertiesToMapper() {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Test
    public void testGetInterviews() throws Exception {
        List<Interview> interviews = new ArrayList<>(List.of(new Interview(LocalDate.now(), "651123456", "abc@miu.edu"), new Interview(LocalDate.now(), "6418191476", "smaka@miu.edu")));

        Mockito.when(interviewService.getInterviews()).thenReturn(interviews);

        mockMvc.perform(MockMvcRequestBuilders.get("/interviews").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].phone", is("651123456")));
    }

    @Test
    public void testCreateInterview() throws Exception {
        Interview interview = new Interview(LocalDate.now(), "651123456", "abc@miu.edu");

        Mockito.when(interviewService.createInterview(ArgumentMatchers.any())).thenReturn(interview);
        String json = mapper.writeValueAsString(interview);
        mockMvc.perform(post("/interviews").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.phone", Matchers.equalTo("651123456"))).andExpect(jsonPath("$.email", Matchers.equalTo("abc@miu.edu")));

    }

    @Test
    public void testUpdateInterview() throws Exception {
        Interview interview = new Interview(LocalDate.now(), "651123456", "abc@miu.edu");

        Mockito.when(interviewService.updateInterview(ArgumentMatchers.anyLong(), ArgumentMatchers.any())).thenReturn(interview);
        String json = mapper.writeValueAsString(interview);
        mockMvc.perform(put("/interviews/1").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.phone", is("651123456"))).andExpect(jsonPath("$.email", is("abc@miu.edu")));
    }

    @Test
    public void testDeleteInterview() throws Exception {
        Mockito.when(interviewService.deleteInterview(1L)).thenReturn("Interview with id 1 deleted successfully");
        MvcResult requestResult = mockMvc.perform(delete("/interviews/1")).andExpect(status().isOk()).andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertEquals(result, "Interview with id 1 deleted successfully");
    }
}

