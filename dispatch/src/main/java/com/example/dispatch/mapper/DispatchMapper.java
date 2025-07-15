package com.example.dispatch.mapper;

import com.example.dispatch.dto.OrdersDTO;
import com.example.dispatch.dto.VehiclesDTO;
import com.example.dispatch.dto.WeatherDto;
import com.example.dispatch.model.Orders;
import com.example.dispatch.model.Vehicles;
import com.example.dispatch.model.WeatherData;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DispatchMapper {
    List<Orders> toOrdersEntity(List<OrdersDTO> dto);

    List<Vehicles> toVehiclesEntity(List<VehiclesDTO> dtos);

    WeatherDto toWeatherDtoList(WeatherData weatherData);

    OrdersDTO toOrdersDto(Orders orders);
}
