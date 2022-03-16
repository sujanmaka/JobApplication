package edu.miu.cs544.sujan.service;

import edu.miu.cs544.sujan.entity.Company;

import java.util.List;

public interface CompanyService {
    Company getCompanyById(Long id);

    List<Company> getCompanies();

    Company createCompany(Company company);

    String deleteCompany(Long id);

    Company updateCompany(Long id, Company company);
}
