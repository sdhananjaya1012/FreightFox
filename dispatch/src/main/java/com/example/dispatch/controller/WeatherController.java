package com.example.dispatch.controller;


import com.example.dispatch.dto.WeatherDto;
import com.example.dispatch.service.WeatherInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("api/weather")
@Slf4j
@AllArgsConstructor
public class WeatherController {

    @Autowired
    private WeatherInfoService weatherService;

    @GetMapping("/get-weather-data")
    public WeatherDto getWeatherData(@RequestParam String pinCode, @RequestParam LocalDate weatherDate) {
        log.info("REST request to get weather data for {} on {}", pinCode, weatherDate);
        return weatherService.getWeatherData(pinCode, weatherDate);
    }
}