package com.example.dispatch.service.ServiceImpl;

import com.example.dispatch.dto.DispatchPlanResponseDTO;
import com.example.dispatch.dto.OrdersDTO;
import com.example.dispatch.dto.VehiclesDTO;
import com.example.dispatch.mapper.DispatchMapper;
import com.example.dispatch.model.Orders;
import com.example.dispatch.model.Vehicles;
import com.example.dispatch.repository.OrdersRepository;
import com.example.dispatch.repository.VehiclesRepository;
import com.example.dispatch.service.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DispatchServiceImpl implements DispatchService {

    @Autowired
    private OrdersRepository orderRepository;

    @Autowired
    private DispatchMapper dispatchMapper; // MapStruct mapper

    @Autowired
    private VehiclesRepository vehiclesRepository;


    @Override
    public void saveOrders(List<OrdersDTO> assignedOrders) {
        orderRepository.saveAll(dispatchMapper.toOrdersEntity(assignedOrders));
    }

    @Override
    public void saveVehicles(List<VehiclesDTO> vehiclesDTOS) {
        vehiclesRepository.saveAll(dispatchMapper.toVehiclesEntity(vehiclesDTOS));
    }

    @Override
    public List<DispatchPlanResponseDTO> generateDispatchPlan() {
        List<Orders> orders = orderRepository.findAllByOrderByOrderPriority();
        List<Vehicles> vehicles = vehiclesRepository.findAll();
        List<DispatchPlanResponseDTO> result = new ArrayList<>();
        Set<String> assignedOrderIds = new HashSet<>();
        for (Vehicles v : vehicles) {
            // Find nearest assignable order
            Orders nearestOrder = null;
            double minDistance = Double.MAX_VALUE;
            for (Orders order : orders) {
                if (!assignedOrderIds.contains(order.getOrderId()) && order.getPackageWeight() <= v.getCapacity()) {
                    double dist = calculateDistance(
                            v.getCurrentLatitude(), v.getCurrentLongitude(),
                            order.getLatitude(), order.getLongitude()
                    );

                    if (dist < minDistance) {
                        nearestOrder = order;
                        minDistance = dist;
                    }
                }

            }
            if (nearestOrder != null) {
                DispatchPlanResponseDTO planResponseDTO = new DispatchPlanResponseDTO();
                planResponseDTO.setVehicleId(v.getVehicleId());
                planResponseDTO.setAssignedOrders(List.of(dispatchMapper.toOrdersDto(nearestOrder)));
                planResponseDTO.setTotalLoad(nearestOrder.getPackageWeight());
                planResponseDTO.setTotalDistance(minDistance);
                assignedOrderIds.add(nearestOrder.getOrderId());
                result.add(planResponseDTO);
            }
        }
        return result;
    }


    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Don't round here
    }


}

