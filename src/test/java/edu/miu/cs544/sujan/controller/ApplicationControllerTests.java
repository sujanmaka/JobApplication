package edu.miu.cs544.sujan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs544.sujan.entity.Application;
import edu.miu.cs544.sujan.entity.Job;
import edu.miu.cs544.sujan.service.ApplicationService;
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


@WebMvcTest(ApplicationController.class)
class ApplicationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApplicationService applicationService;

    private static ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void addPropertiesToMapper() {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Test
    public void testGetApplications() throws Exception {
        List<Application> applications = new ArrayList<>(List.of(
                new Application(LocalDate.now(), "1", new Job("Spring Developer", 40000.0)),
                new Application(LocalDate.now(), "2", new Job("Frontend Developer", 30000.0))));

        Mockito.when(applicationService.getApplications()).thenReturn(applications);

        mockMvc.perform(MockMvcRequestBuilders.get("/applications").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].job.title", is("Spring Developer")));
    }

    @Test
    public void testCreateApplication() throws Exception {
        Application Application = new Application(LocalDate.now(), "1", new Job("Spring Developer", 40000.0));

        Mockito.when(applicationService.createApplication(ArgumentMatchers.any())).thenReturn(Application);
        String json = mapper.writeValueAsString(Application);
        mockMvc.perform(post("/applications").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.job.title", Matchers.equalTo("Spring Developer")))
                .andExpect(jsonPath("$.job.salary", Matchers.equalTo(40000.0)));

    }

    @Test
    public void testUpdateApplication() throws Exception {
        Application application = new Application(LocalDate.now(), "1", new Job("Spring Developer", 40000.0));

        Mockito.when(applicationService.updateApplication(ArgumentMatchers.anyLong(), ArgumentMatchers.any())).thenReturn(application);
        String json = mapper.writeValueAsString(application);
        mockMvc.perform(put("/applications/1").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(json)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.job.title", is("Spring Developer"))).andExpect(jsonPath("$.job.salary", is(40000.0)));
    }

    @Test
    public void testDeleteApplication() throws Exception {
        Mockito.when(applicationService.deleteApplication(1L)).thenReturn("Application with id 1 deleted successfully");
        MvcResult requestResult = mockMvc.perform(delete("/applications/1")).andExpect(status().isOk()).andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertEquals(result, "Application with id 1 deleted successfully");
    }
}

