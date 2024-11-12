package org.example.carsharing.controllers;

import org.example.carsharing.dto.BookingDTO;
import org.example.carsharing.dto.CarDTO;
import org.example.carsharing.services.BookingService;
import org.example.carsharing.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<List<CarDTO>> getCars() {
        ResponseEntity<List<CarDTO>> responseEntity;
        responseEntity = carService.findAll();
        return responseEntity;
    }
}
