package org.example.carsharing.services;

import org.example.carsharing.constants.CarClass;
import org.example.carsharing.constants.CarStatus;
import org.example.carsharing.dto.BookingDTO;
import org.example.carsharing.dto.CarDTO;
import org.example.carsharing.models.CarEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CarService {
    ResponseEntity<List<CarDTO>> findAll();
    ResponseEntity<CarDTO> updateStatus(Long id, CarStatus carStatus);
    ResponseEntity<BookingDTO> rentCar(String number, Long carId);
    ResponseEntity<BookingDTO> returnCar(Long carId, Long bookingId, String carAddress);
    List<CarDTO> getFreeCars();
    ResponseEntity<List<CarDTO>> getFreeCarsByCarClass(CarClass carClass);
    ResponseEntity<List<CarDTO>> getAllCarsByCarClass(CarClass carClass);
    ResponseEntity<List<CarDTO>> getAllCarsByStatus(CarStatus carStatus);
    ResponseEntity<CarDTO> createCar(CarDTO carDTO);
    ResponseEntity<CarDTO> updateCar(CarDTO carDTO);
    void deleteCar(Long id);
    CarDTO getCar (Long id);
}
