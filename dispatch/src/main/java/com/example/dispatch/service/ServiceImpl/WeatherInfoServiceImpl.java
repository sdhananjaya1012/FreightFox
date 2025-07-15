package com.example.dispatch.service.ServiceImpl;

import com.example.dispatch.dto.WeatherDto;
import com.example.dispatch.feign.OpenWeatherFeign;
import com.example.dispatch.mapper.DispatchMapper;
import com.example.dispatch.model.Pincodes;
import com.example.dispatch.model.WeatherData;
import com.example.dispatch.repository.PincodeRepository;
import com.example.dispatch.repository.WeatherDataRepository;
import com.example.dispatch.service.WeatherInfoService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WeatherInfoServiceImpl implements WeatherInfoService {

    @Value("${apis.openWeather.api-key}")
    private String openWeatherApiKey;

    @Autowired
    private PincodeRepository pincodeRepository;

    @Autowired
    OpenWeatherFeign openWeatherFeign;

    @Autowired
    WeatherDataRepository weatherDataRepository;

    @Autowired
    DispatchMapper dispatchMapper;


    @Override
    public WeatherDto getWeatherData(String pinCode, LocalDate weatherDate) {
        log.info("Getting weather data for {} on {}", pinCode, weatherDate);
        Optional<WeatherData> weatherDataList = weatherDataRepository.findByPincodeAndForDate(pinCode,weatherDate);
        if (weatherDataList.isPresent()) {
            log.info("Found pincode in table for: {}", pinCode);
            return dispatchMapper.toWeatherDtoList(weatherDataList.get());
        }
        String latLong = getLatLong(pinCode);
        String[] latLongSep = latLong.split(",");
        log.info("Lat Long: {}", latLongSep[0] + " " + latLongSep[1]);
        JsonObject response = openWeatherFeign.getWeatherData(latLongSep[0], latLongSep[1], openWeatherApiKey,"metric");
        WeatherData weatherData = saveWeatherData(pinCode, weatherDate, response, latLongSep);
        return dispatchMapper.toWeatherDtoList(weatherData);
    }

    private WeatherData saveWeatherData(String pinCode, LocalDate weatherDate, JsonObject response, String[] latLongSep) {
        log.info("Saving weather data for {} on {}", pinCode, weatherDate);
        WeatherData weatherData = new WeatherData();
        weatherData.setPincode(pinCode);
        weatherData.setTemperature(response.get("main").getAsJsonObject().get("temp").getAsDouble());
        weatherData.setDescription(response.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString());
        weatherData.setHumidity(response.get("main").getAsJsonObject().get("humidity").getAsDouble());
        weatherData.setForDate(weatherDate);
        log.info("Saving weather data {}", weatherData);
        weatherDataRepository.save(weatherData);
        Pincodes pincodeData = new Pincodes();
        pincodeData.setPincode(pinCode);
        pincodeData.setLatitude(Double.valueOf(latLongSep[0]));
        pincodeData.setLongitude(Double.valueOf(latLongSep[1]));
        log.info("Saving pincode {}", pincodeData);
        pincodeRepository.save(pincodeData);
        return weatherData;
    }

    private String getLatLong(String pinCode) {
        log.info("Getting lat long for {}", pinCode);
        String pinCodeWithCountryCode = pinCode + ",IN";
        JsonObject geoDecoding = openWeatherFeign.getGeoDecoding(pinCodeWithCountryCode, openWeatherApiKey);
        return geoDecoding.get("lat").getAsString() + "," + geoDecoding.get("lon").getAsString();
    }

}
