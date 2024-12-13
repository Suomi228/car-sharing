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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder =passwordEncoder;
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
    @Override
    public void registerCustomer(CustomerDTO customerDTO, String rawPassword) {

        Optional<CustomerEntity> existCustomer = customerRepository.findByNumber(customerDTO.getNumber());
        if (existCustomer.isPresent()){
            throw new RuntimeException("number.used");
        }

        CustomerEntity customer = new CustomerEntity();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setNumber(customerDTO.getNumber());
        customer.setAdmin(false);
        customer.setPassword(passwordEncoder.encode(rawPassword));

        customerRepository.save(customer);
    }

    @Override
    public CustomerDTO findByNumber(String number) {
        CustomerEntity customerEntity = customerRepository.findByNumber(number).orElseThrow();
        CustomerDTO customerDTO = modelMapper.map(customerEntity, CustomerDTO.class);
        return customerDTO;
    }
}
