package edu.miu.cs544.sujan.repository;

import edu.miu.cs544.sujan.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
