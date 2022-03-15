package edu.miu.cs544.sujan.repository;

import edu.miu.cs544.sujan.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
