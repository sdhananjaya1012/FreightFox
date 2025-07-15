package com.example.dispatch.repository;


import com.example.dispatch.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {

    Optional<WeatherData> findByPincodeAndForDate(String pincode, LocalDate forDate);


}