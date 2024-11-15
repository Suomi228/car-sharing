package org.example.carsharing.services;

import org.example.carsharing.constants.CarStatus;
import org.example.carsharing.dto.CarDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CarService {
    ResponseEntity<List<CarDTO>> findAll();
    ResponseEntity<CarDTO> updateStatus(Long id, CarStatus carStatus);
}
