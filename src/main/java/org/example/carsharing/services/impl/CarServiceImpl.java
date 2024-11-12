package org.example.carsharing.services.impl;

import org.example.carsharing.dto.BookingDTO;
import org.example.carsharing.dto.CarDTO;
import org.example.carsharing.models.BookingEntity;
import org.example.carsharing.models.CarEntity;
import org.example.carsharing.repositories.BookingRepository;
import org.example.carsharing.repositories.CarRepository;
import org.example.carsharing.services.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
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
}
