package org.example.carsharing.services;

import org.example.carsharing.dto.PaymentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface PaymentService {
    ResponseEntity<List<PaymentDTO>> findAll();
}
