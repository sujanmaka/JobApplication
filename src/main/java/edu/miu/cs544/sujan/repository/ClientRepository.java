package edu.miu.cs544.sujan.repository;

import edu.miu.cs544.sujan.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
