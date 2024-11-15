package org.example.carsharing.services;

import org.example.carsharing.dto.BookingDTO;
import org.example.carsharing.models.BookingEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookingService {
    ResponseEntity<List<BookingDTO>> findAll();
    ResponseEntity<List<BookingDTO>> findByCustomerId(Long customerId);
}
