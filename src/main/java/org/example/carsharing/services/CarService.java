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
    List<CarDTO> findAll();
    CarDTO updateStatus(Long id, CarStatus carStatus);
    BookingDTO rentCar(String number, Long carId);
    BookingDTO returnCar(String number, Long carId, Long bookingId, String carAddress);
    List<CarDTO> getFreeCars();
    List<CarDTO> getFreeCarsByCarClass(CarClass carClass);
    List<CarDTO> getAllCarsByCarClass(CarClass carClass);
    List<CarDTO> getAllCarsByStatus(CarStatus carStatus);
    CarDTO createCar(CarDTO carDTO);
    CarDTO updateCar(CarDTO carDTO);
    void deleteCar(Long id);
    CarDTO getCar (Long id);

}
