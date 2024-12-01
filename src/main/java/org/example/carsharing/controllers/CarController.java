package org.example.carsharing.controllers;

import org.example.carsharing.constants.CarClass;
import org.example.carsharing.constants.CarStatus;
import org.example.carsharing.dto.BookingDTO;
import org.example.carsharing.dto.CarDTO;
import org.example.carsharing.dto.CarReturnRequestDTO;
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

    @GetMapping(path = "/allCars")
    public ResponseEntity<List<CarDTO>> getAllCars() {
        ResponseEntity<List<CarDTO>> responseEntity = carService.findAll();
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
        ResponseEntity<BookingDTO> responseEntity;
        responseEntity = carService.rentCar(customerId, carId);
        return responseEntity;
    }

    @PostMapping("/return")
    public ResponseEntity<BookingDTO> returnCar(@RequestBody CarReturnRequestDTO requestDTO) {
        ResponseEntity<BookingDTO> responseEntity;
        responseEntity = carService.returnCar(
                requestDTO.getCarId(),
                requestDTO.getBookingId(),
                requestDTO.getCarAddress()
        );
        return responseEntity;
    }

    @GetMapping("/free")
    public ResponseEntity<List<CarDTO>> getFreeCars() {
        ResponseEntity<List<CarDTO>> responseEntity = carService.getFreeCars();
        return responseEntity;
    }
    @GetMapping("/free/{carClass}")
    public ResponseEntity<List<CarDTO>> getFreeCarsByCarClass(@PathVariable CarClass carClass) {
        ResponseEntity<List<CarDTO>> responseEntity = carService.getFreeCarsByCarClass(carClass);
        return responseEntity;
    }
    @GetMapping("/allCarsByCarClass/{carClass}")
    public ResponseEntity<List<CarDTO>> getAllCarsByCarClass(@PathVariable CarClass carClass) {
        ResponseEntity<List<CarDTO>> responseEntity = carService.getAllCarsByCarClass(carClass);
        return responseEntity;
    }
    @GetMapping("/allCarsByStatus/{carStatus}")
    public ResponseEntity<List<CarDTO>> getAllCarsByStatus(@PathVariable CarStatus carStatus) {
        ResponseEntity<List<CarDTO>> responseEntity = carService.getAllCarsByStatus(carStatus);
        return responseEntity;
    }
    @PostMapping("/create")
    public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO carDTO) {
        ResponseEntity<CarDTO> responseEntity = carService.createCar(carDTO);
        return responseEntity;
    }
    @PutMapping("/update")
    public ResponseEntity<CarDTO> updateCar(@RequestBody CarDTO carDTO) {
        ResponseEntity<CarDTO> responseEntity = carService.updateCar(carDTO);
        return responseEntity;
    }
    @DeleteMapping("/delete")
    public void deleteCar(@RequestParam Long id) {
        carService.deleteCar(id);
    }
}
