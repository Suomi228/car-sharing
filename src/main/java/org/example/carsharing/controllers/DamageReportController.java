package org.example.carsharing.controllers;

import org.example.carsharing.dto.CustomerDTO;
import org.example.carsharing.dto.DamageReportDTO;
import org.example.carsharing.services.CustomerService;
import org.example.carsharing.services.DamageReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/damageReports")
public class DamageReportController {
    private final DamageReportService damageReportService;

    @Autowired
    public DamageReportController(DamageReportService damageReportService) {
        this.damageReportService = damageReportService;
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<List<DamageReportDTO>> getCustomers() {
        ResponseEntity<List<DamageReportDTO>> responseEntity;
        responseEntity = damageReportService.findAll();
        return responseEntity;
    }
}
