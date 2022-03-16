package edu.miu.cs544.sujan.service.impl;

import edu.miu.cs544.sujan.entity.Company;
import edu.miu.cs544.sujan.exception.DataNotFoundException;
import edu.miu.cs544.sujan.exception.ReferentialIntegrityException;
import edu.miu.cs544.sujan.repository.CompanyRepository;
import edu.miu.cs544.sujan.service.CompanyService;
import edu.miu.cs544.sujan.util.CustomNullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company getCompanyById(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            return company.get();
        } else {
            throw new DataNotFoundException(String.format("Company with id %d not found.", id));
        }
    }

    @Override
    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public String deleteCompany(Long id) {
        try {
            companyRepository.delete(getCompanyById(id));
            return String.format("Company with id %d deleted successfully", id);
        } catch (Exception exception) {
            throw new ReferentialIntegrityException(String.format("Company with id %d is referenced by other entity.", id));
        }
    }

    @Override
    public Company updateCompany(Long id, Company company) {
        Company currentCompany = getCompanyById(id);
        CustomNullAwareBeanUtils.myCopyProperties(company, currentCompany);
        return companyRepository.save(currentCompany);
    }
}
