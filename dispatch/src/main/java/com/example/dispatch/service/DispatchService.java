package com.example.dispatch.service;

import com.example.dispatch.dto.DispatchPlanResponseDTO;
import com.example.dispatch.dto.OrdersDTO;
import com.example.dispatch.dto.VehiclesDTO;

import java.util.List;

public interface DispatchService {
    void saveOrders(List<OrdersDTO> assignedOrders);
    void saveVehicles(List<VehiclesDTO> vehiclesDTOS);
    List<DispatchPlanResponseDTO> generateDispatchPlan();
}
