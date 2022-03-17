package edu.miu.cs544.sujan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cs544.sujan.entity.Address;
import edu.miu.cs544.sujan.entity.Company;
import edu.miu.cs544.sujan.service.CompanyService;
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


@WebMvcTest(CompanyController.class)
class CompanyControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetCompanys() throws Exception {
        List<Company> companies = new ArrayList<>(List.of(new Company("Metaverse LLC", new Address("57 kilvert", "Fairfield", "52557", "IA")), new Company("XYZ LLC", new Address("New Road", "Kathmandu", "54000", "GA"))));

        Mockito.when(companyService.getCompanies()).thenReturn(companies);

        mockMvc.perform(MockMvcRequestBuilders.get("/companies").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].name", is("Metaverse LLC")));
    }

    @Test
    public void testCreateCompany() throws Exception {
        Company company =new Company("Metaverse LLC", new Address("57 kilvert", "Fairfield", "52557", "IA"));

        Mockito.when(companyService.createCompany(ArgumentMatchers.any())).thenReturn(company);
        String json = mapper.writeValueAsString(company);
        mockMvc.perform(post("/companies").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.name", Matchers.equalTo("Metaverse LLC"))).andExpect(jsonPath("$.address.city", Matchers.equalTo("Fairfield")));

    }

    @Test
    public void testUpdateCompany() throws Exception {
        Company company =new Company("Metaverse LLC", new Address("57 kilvert", "Fairfield", "52557", "IA"));

        Mockito.when(companyService.updateCompany(ArgumentMatchers.anyLong(), ArgumentMatchers.any())).thenReturn(company);
        String json = mapper.writeValueAsString(company);
        mockMvc.perform(put("/companies/1").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.name", is("Metaverse LLC"))).andExpect(jsonPath("$.address.city", is("Fairfield")));
    }

    @Test
    public void testDeleteCompany() throws Exception {
        Mockito.when(companyService.deleteCompany(1L)).thenReturn("Company with id 1 deleted successfully");
        MvcResult requestResult = mockMvc.perform(delete("/companies/1")).andExpect(status().isOk()).andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertEquals(result, "Company with id 1 deleted successfully");
    }
}

