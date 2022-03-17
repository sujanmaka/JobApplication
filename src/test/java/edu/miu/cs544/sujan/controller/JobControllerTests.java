package edu.miu.cs544.sujan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cs544.sujan.entity.Job;
import edu.miu.cs544.sujan.service.JobService;
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


@WebMvcTest(JobController.class)
class JobControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobService jobService;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetJobs() throws Exception {
        List<Job> jobs = new ArrayList<>(List.of(new Job("Spring Developer", 40000.0), new Job("Frontend Developer", 30000.0)));

        Mockito.when(jobService.getJobs()).thenReturn(jobs);

        mockMvc.perform(MockMvcRequestBuilders.get("/jobs").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].title", is("Spring Developer")));
    }

    @Test
    public void testCreateJob() throws Exception {
        Job job = new Job("Software Architect", 60000.0);

        Mockito.when(jobService.createJob(ArgumentMatchers.any())).thenReturn(job);
        String json = mapper.writeValueAsString(job);
        mockMvc.perform(post("/jobs").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.title", Matchers.equalTo("Software Architect"))).andExpect(jsonPath("$.salary", Matchers.equalTo(60000.0)));

    }

    @Test
    public void testUpdateJob() throws Exception {
        Job job = new Job("Software Tester", 60000.0);

        Mockito.when(jobService.updateJob(ArgumentMatchers.anyLong(), ArgumentMatchers.any())).thenReturn(job);
        String json = mapper.writeValueAsString(job);
        mockMvc.perform(put("/jobs/1").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Software Tester")))
                .andExpect(jsonPath("$.salary", is(60000.0)));
    }

    @Test
    public void testDeleteJob() throws Exception {
        Mockito.when(jobService.deleteJob(1L)).thenReturn("Job with id 1 deleted successfully");
        MvcResult requestResult = mockMvc.perform(delete("/jobs/1")).andExpect(status().isOk()).andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertEquals(result, "Job with id 1 deleted successfully");
    }
}

