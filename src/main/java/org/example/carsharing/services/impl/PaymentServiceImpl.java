package org.example.carsharing.services.impl;

import org.example.carsharing.dto.PaymentDTO;
import org.example.carsharing.models.PaymentEntity;
import org.example.carsharing.repositories.PaymentRepository;
import org.example.carsharing.services.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, ModelMapper modelMapper) {
        this.paymentRepository = paymentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<PaymentDTO>> findAll() {
        List<PaymentEntity> paymentEntities = paymentRepository.findAll();
        List<PaymentDTO> paymentDTOS = paymentEntities.stream()
                .map(payment -> modelMapper.map(payment, PaymentDTO.class))
                .toList();
        ResponseEntity<List<PaymentDTO>> responseEntity = ResponseEntity.ok().body(paymentDTOS);
        return responseEntity;
    }
}
