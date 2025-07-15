package com.example.dispatch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDto {

    private String pincode;

    private LocalDate forDate;

    private String description;

    private Double temperature;

    private Double humidity;
}
