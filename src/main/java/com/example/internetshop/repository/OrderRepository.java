package com.example.internetshop.repository;

import com.example.internetshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Transactional
    Order findOrderById(Long id);
}
