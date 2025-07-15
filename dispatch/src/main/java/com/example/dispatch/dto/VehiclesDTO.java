package com.example.dispatch.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiclesDTO {

    private String vehicleId;
    private Double capacity;
    private Double currentLatitude;
    private Double currentLongitude;
    private String currentAddress;
}
