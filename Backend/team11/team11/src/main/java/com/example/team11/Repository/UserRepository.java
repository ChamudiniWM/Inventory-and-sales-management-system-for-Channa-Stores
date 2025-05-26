package com.example.team11.Repository;

import com.example.team11.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email); // For login or lookup
}
