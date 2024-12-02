package org.example.carsharing.services.impl;

import org.example.carsharing.dto.CarDTO;
import org.example.carsharing.dto.CustomerDTO;
import org.example.carsharing.models.CarEntity;
import org.example.carsharing.models.CustomerEntity;
import org.example.carsharing.repositories.CarRepository;
import org.example.carsharing.repositories.CustomerRepository;
import org.example.carsharing.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<CustomerDTO>> findAll() {
        List<CustomerEntity> customerEntities = customerRepository.findAll();
        List<CustomerDTO> customerDTOS = customerEntities.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .toList();
        ResponseEntity<List<CustomerDTO>> responseEntity = ResponseEntity.ok().body(customerDTOS);
        return responseEntity;
    }

    @Override
    public CustomerDTO findById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id);
        CustomerDTO customerDTO = modelMapper.map(customerEntity, CustomerDTO.class);
        return customerDTO;
    }
}
