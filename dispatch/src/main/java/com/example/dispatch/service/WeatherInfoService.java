package com.example.dispatch.service;

import com.example.dispatch.dto.WeatherDto;

import java.time.LocalDate;

public interface WeatherInfoService {
    WeatherDto getWeatherData(String pinCode, LocalDate weatherDate);

}
