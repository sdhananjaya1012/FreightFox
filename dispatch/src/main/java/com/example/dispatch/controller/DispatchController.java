package com.example.dispatch.controller;

import com.example.dispatch.dto.ApiResponseDto;
import com.example.dispatch.dto.DispatchPlanResponseDTO;
import com.example.dispatch.dto.OrdersDTO;
import com.example.dispatch.dto.VehiclesDTO;
import com.example.dispatch.service.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/dispatch")
public class DispatchController {

    @Autowired
    private DispatchService dispatchService;

    @RequestMapping(value = "/orders", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto> addOrders(@RequestBody List<OrdersDTO> ordersDTOS) {
        try {
            dispatchService.saveOrders(ordersDTOS);
            return ResponseEntity.ok(new ApiResponseDto("Delivery orders accepted.", "success"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseDto("Failed to save delivery orders.", "error"));
        }
    }


    @RequestMapping(value = "/vehicles", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto> addVehicles(@RequestBody List<VehiclesDTO> vehiclesDTOList) {
        try {
            dispatchService.saveVehicles(vehiclesDTOList);
            return ResponseEntity.ok(new ApiResponseDto("Vehicle details accepted", "success"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseDto("Failed to save Vehicle details .", "error"));
        }
    }



        @RequestMapping(value = "/plan", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<DispatchPlanResponseDTO>> getClientsByFamilyId() {
            try {
                List <DispatchPlanResponseDTO> dtos=dispatchService.generateDispatchPlan();
                return new ResponseEntity<>(dtos, HttpStatus.OK);
            } catch (Exception ex) {
                return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);

            }

        }

    }









