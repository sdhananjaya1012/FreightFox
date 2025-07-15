package com.example.dispatch.mapper;

import com.example.dispatch.dto.OrdersDTO;
import com.example.dispatch.dto.VehiclesDTO;
import com.example.dispatch.dto.WeatherDto;
import com.example.dispatch.model.Orders;
import com.example.dispatch.model.Vehicles;
import com.example.dispatch.model.WeatherData;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-15T09:17:22+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class DispatchMapperImpl implements DispatchMapper {

    @Override
    public List<Orders> toOrdersEntity(List<OrdersDTO> dto) {
        if ( dto == null ) {
            return null;
        }

        List<Orders> list = new ArrayList<Orders>( dto.size() );
        for ( OrdersDTO ordersDTO : dto ) {
            list.add( ordersDTOToOrders( ordersDTO ) );
        }

        return list;
    }

    @Override
    public List<Vehicles> toVehiclesEntity(List<VehiclesDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Vehicles> list = new ArrayList<Vehicles>( dtos.size() );
        for ( VehiclesDTO vehiclesDTO : dtos ) {
            list.add( vehiclesDTOToVehicles( vehiclesDTO ) );
        }

        return list;
    }

    @Override
    public WeatherDto toWeatherDtoList(WeatherData weatherData) {
        if ( weatherData == null ) {
            return null;
        }

        WeatherDto weatherDto = new WeatherDto();

        weatherDto.setPincode( weatherData.getPincode() );
        weatherDto.setForDate( weatherData.getForDate() );
        weatherDto.setDescription( weatherData.getDescription() );
        weatherDto.setTemperature( weatherData.getTemperature() );
        weatherDto.setHumidity( weatherData.getHumidity() );

        return weatherDto;
    }

    @Override
    public OrdersDTO toOrdersDto(Orders orders) {
        if ( orders == null ) {
            return null;
        }

        OrdersDTO ordersDTO = new OrdersDTO();

        ordersDTO.setOrderId( orders.getOrderId() );
        if ( orders.getLatitude() != null ) {
            ordersDTO.setLatitude( orders.getLatitude() );
        }
        if ( orders.getLongitude() != null ) {
            ordersDTO.setLongitude( orders.getLongitude() );
        }
        ordersDTO.setAddress( orders.getAddress() );
        if ( orders.getPackageWeight() != null ) {
            ordersDTO.setPackageWeight( orders.getPackageWeight() );
        }
        ordersDTO.setOrderPriority( orders.getOrderPriority() );

        return ordersDTO;
    }

    protected Orders ordersDTOToOrders(OrdersDTO ordersDTO) {
        if ( ordersDTO == null ) {
            return null;
        }

        Orders orders = new Orders();

        orders.setOrderId( ordersDTO.getOrderId() );
        orders.setLatitude( ordersDTO.getLatitude() );
        orders.setLongitude( ordersDTO.getLongitude() );
        orders.setAddress( ordersDTO.getAddress() );
        orders.setPackageWeight( ordersDTO.getPackageWeight() );
        orders.setOrderPriority( ordersDTO.getOrderPriority() );

        return orders;
    }

    protected Vehicles vehiclesDTOToVehicles(VehiclesDTO vehiclesDTO) {
        if ( vehiclesDTO == null ) {
            return null;
        }

        Vehicles vehicles = new Vehicles();

        vehicles.setVehicleId( vehiclesDTO.getVehicleId() );
        vehicles.setCapacity( vehiclesDTO.getCapacity() );
        vehicles.setCurrentLatitude( vehiclesDTO.getCurrentLatitude() );
        vehicles.setCurrentLongitude( vehiclesDTO.getCurrentLongitude() );
        vehicles.setCurrentAddress( vehiclesDTO.getCurrentAddress() );

        return vehicles;
    }
}
