package com.example.dispatch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DispatchPlanResponseDTO {
    private String vehicleId;
    private double totalLoad;
    private double totalDistance;
    private List<OrdersDTO> assignedOrders;
}