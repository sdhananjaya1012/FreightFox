package com.example.dispatch.repository;

import com.example.dispatch.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByOrderByOrderPriority();
}