package com.example.team11.Repository;

import com.example.team11.Entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Supplier findByUserEmail(String email);
}
