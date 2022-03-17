package edu.miu.cs544.sujan.service;


import edu.miu.cs544.sujan.entity.Address;
import edu.miu.cs544.sujan.entity.Company;
import edu.miu.cs544.sujan.repository.CompanyRepository;
import edu.miu.cs544.sujan.service.impl.CompanyServiceImpl;
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
public class CompanyTests {
    @Mock
    CompanyRepository companyRepository;

    @InjectMocks
    CompanyServiceImpl companyService;

    @Test
    public void testGetCompanies() {
        List<Company> companies = new ArrayList<>();
        companies.add(new Company("Metaverse LLC", new Address("57 kilvert", "Fairfield", "52557", "IA")));
        Mockito.when(companyRepository.findAll()).thenReturn(companies);
        List<Company> expected = companyService.getCompanies();
        assertEquals(expected, companies);
    }

    @Test
    public void testGetCompanyById() {
        Company company = new Company("Metaverse LLC", new Address("57 kilvert", "Fairfield", "52557", "IA"));
        Mockito.when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        Company expected = companyService.getCompanyById(1L);
        assertEquals(expected, company);
    }

    @Test
    public void testCreateCompany() {
        Company company = new Company("Metaverse LLC", new Address("57 kilvert", "Fairfield", "52557", "IA"));
        Mockito.when(companyRepository.save(company)).thenReturn(company);
        Company createdCompany = companyService.createCompany(company);
        assertEquals(createdCompany.getName(), company.getName());
    }

    @Test
    public void testDeleteCompany() {
        Company company = new Company("Metaverse LLC", new Address("57 kilvert", "Fairfield", "52557", "IA"));
        Mockito.when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        String message = companyService.deleteCompany(1L);
        assertEquals("Company with id 1 deleted successfully", message);
    }

    @Test
    public void testUpdateCompany() {
        Company company = new Company("Metaverse LLC", new Address("57 kilvert", "Fairfield", "52557", "IA"));
        Mockito.when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        Mockito.when(companyRepository.save(company)).thenReturn(company);
        Company updatedCompany = companyService.updateCompany(1L, company);
        assertEquals(company, updatedCompany);
    }
}
