package com.example.dispatch.dto;

import com.example.dispatch.enumeration.OrderPriority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
    private String orderId;
    private double latitude;
    private double longitude;
    private String address;
    private double packageWeight;
    private OrderPriority orderPriority;
}