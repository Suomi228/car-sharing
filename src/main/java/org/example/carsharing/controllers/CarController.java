package org.example.carsharing.controllers;

import org.example.carsharing.constants.CarStatus;
import org.example.carsharing.dto.BookingDTO;
import org.example.carsharing.dto.CarDTO;
import org.example.carsharing.services.BookingService;
import org.example.carsharing.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping(path = "/updateStatus")
    public ResponseEntity<CarDTO> updateStatus(@RequestParam Long id,@RequestParam CarStatus status) {
        ResponseEntity<CarDTO> responseEntity;
        responseEntity = carService.updateStatus(id, status);
        return responseEntity;
    }

    @PostMapping("/rent")
    public ResponseEntity<BookingDTO> rentCar(@RequestParam Long customerId, @RequestParam Long carId) {
        System.out.println("Controller triggered: customerId=" + customerId + ", carId=" + carId);
        ResponseEntity<BookingDTO> responseEntity;
        responseEntity = carService.rentCar(customerId, carId);
        return responseEntity;
    }

    @PostMapping("/return")
    public ResponseEntity<BookingDTO> returnCar(
            @RequestParam Long carId,
            @RequestParam Long bookingId,
            @RequestParam String carAddress) {
        ResponseEntity<BookingDTO> responseEntity;
        responseEntity = carService.returnCar(carId, bookingId, carAddress);
        return responseEntity;
    }
}
