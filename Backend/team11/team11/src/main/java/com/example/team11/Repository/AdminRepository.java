package com.example.team11.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.team11.Entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
