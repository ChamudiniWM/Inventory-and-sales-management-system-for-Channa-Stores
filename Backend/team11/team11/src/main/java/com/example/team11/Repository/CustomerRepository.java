package com.example.team11.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.team11.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
