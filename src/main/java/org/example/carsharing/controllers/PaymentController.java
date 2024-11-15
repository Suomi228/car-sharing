package org.example.carsharing.controllers;

import org.example.carsharing.dto.DamageReportDTO;
import org.example.carsharing.dto.PaymentDTO;
import org.example.carsharing.services.DamageReportService;
import org.example.carsharing.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<List<PaymentDTO>> getPayments() {
        ResponseEntity<List<PaymentDTO>> responseEntity;
        responseEntity = paymentService.findAll();
        return responseEntity;
    }
}
