package edu.miu.cs544.sujan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cs544.sujan.entity.Skill;
import edu.miu.cs544.sujan.service.SkillService;
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


@WebMvcTest(SkillController.class)
class SkillControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SkillService skillService;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetSkills() throws Exception {
        List<Skill> skills = new ArrayList<>(List.of(new Skill("Coding", "4 years", "Smart coder", "Java"), new Skill("Tester", "5 years", "Cool tester", "QA/QC")));

        Mockito.when(skillService.getSkills()).thenReturn(skills);

        mockMvc.perform(MockMvcRequestBuilders.get("/skills").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].name", is("Coding")));
    }

    @Test
    public void testCreateSkill() throws Exception {
        Skill skill = new Skill("Coding", "4 years", "Smart coder", "Java");

        Mockito.when(skillService.createSkill(ArgumentMatchers.any())).thenReturn(skill);
        String json = mapper.writeValueAsString(skill);
        mockMvc.perform(post("/skills").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.name", Matchers.equalTo("Coding"))).andExpect(jsonPath("$.language", Matchers.equalTo("Java")));

    }

    @Test
    public void testUpdateSkill() throws Exception {
        Skill skill = new Skill("Coding", "4 years", "Smart coder", "Java");

        Mockito.when(skillService.updateSkill(ArgumentMatchers.anyLong(), ArgumentMatchers.any())).thenReturn(skill);
        String json = mapper.writeValueAsString(skill);
        mockMvc.perform(put("/skills/1").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.name", is("Coding"))).andExpect(jsonPath("$.language", is("Java")));
    }

    @Test
    public void testDeleteSkill() throws Exception {
        Mockito.when(skillService.deleteSkill(1L)).thenReturn("Skill with id 1 deleted successfully");
        MvcResult requestResult = mockMvc.perform(delete("/skills/1")).andExpect(status().isOk()).andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertEquals(result, "Skill with id 1 deleted successfully");
    }
}

