package com.example.team11.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.team11.Entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
