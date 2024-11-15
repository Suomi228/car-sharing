package org.example.carsharing.services.impl;

import org.example.carsharing.constants.BookingStatus;
import org.example.carsharing.constants.CarStatus;
import org.example.carsharing.dto.BookingDTO;
import org.example.carsharing.dto.CarDTO;
import org.example.carsharing.models.BookingEntity;
import org.example.carsharing.models.CarEntity;
import org.example.carsharing.models.CustomerEntity;
import org.example.carsharing.repositories.BookingRepository;
import org.example.carsharing.repositories.CarRepository;
import org.example.carsharing.repositories.CustomerRepository;
import org.example.carsharing.services.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, CustomerRepository customerRepository, BookingRepository bookingRepository) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public ResponseEntity<List<CarDTO>> findAll() {
        List<CarEntity> carEntities = carRepository.findAll();
        List<CarDTO> carDTOS = carEntities.stream()
                .map(car -> modelMapper.map(car, CarDTO.class))
                .toList();
        ResponseEntity<List<CarDTO>> responseEntity = ResponseEntity.ok().body(carDTOS);
        return responseEntity;
    }

    @Override
    public ResponseEntity<CarDTO> updateStatus(Long id, CarStatus carStatus) {
        CarEntity carEntity = carRepository.findById(id);
        carEntity.changeStatus(carStatus);
        CarEntity savedOrderEntity = carRepository.save(carEntity);
        CarDTO carDTO = modelMapper.map(savedOrderEntity, CarDTO.class);
        ResponseEntity<CarDTO> responseEntity = ResponseEntity.ok().body(carDTO);
        return responseEntity;
    }
    @Override
    public ResponseEntity<String> rentCar(Long customerId, Long carId) {
        CarEntity car = carRepository.findById(carId);
        if (car == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Car not found with id: " + carId);
        }

        CustomerEntity customer = customerRepository.findById(customerId);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer not found with id: " + customerId);
        }

        if (car.getStatus() == CarStatus.USED) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Car is already rented");
        }

        car.changeStatus(CarStatus.USED);
        carRepository.save(car);

        BookingEntity booking = new BookingEntity();
        booking.setCar(car);
        booking.setCustomer(customer);
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setStartDate("2024-11-15");
        bookingRepository.save(booking);

        return ResponseEntity.ok("Автомобиль успешно арендован");
    }

}
