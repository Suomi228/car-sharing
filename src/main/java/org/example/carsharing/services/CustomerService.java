package org.example.carsharing.services;

import org.example.carsharing.dto.CustomerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CustomerService {
    ResponseEntity<List<CustomerDTO>> findAll();
    CustomerDTO findById(Long id);
}
