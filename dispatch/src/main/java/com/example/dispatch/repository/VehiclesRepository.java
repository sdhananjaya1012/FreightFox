package com.example.dispatch.repository;

import com.example.dispatch.model.Orders;
import com.example.dispatch.model.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiclesRepository extends JpaRepository<Vehicles, Long> {
}