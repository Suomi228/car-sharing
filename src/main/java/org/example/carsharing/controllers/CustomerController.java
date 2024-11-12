package org.example.carsharing.controllers;

import org.example.carsharing.dto.CarDTO;
import org.example.carsharing.dto.CustomerDTO;
import org.example.carsharing.services.CarService;
import org.example.carsharing.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<List<CustomerDTO>> getCustomers() {
        ResponseEntity<List<CustomerDTO>> responseEntity;
        responseEntity = customerService.findAll();
        return responseEntity;
    }
}
