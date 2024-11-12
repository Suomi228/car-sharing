package org.example.carsharing.services.impl;

import org.example.carsharing.dto.CustomerDTO;
import org.example.carsharing.dto.DamageReportDTO;
import org.example.carsharing.models.CustomerEntity;
import org.example.carsharing.models.DamageReportEntity;
import org.example.carsharing.repositories.CustomerRepository;
import org.example.carsharing.repositories.DamageReportRepository;
import org.example.carsharing.services.DamageReportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamageReportServiceImpl implements DamageReportService {

    private final DamageReportRepository damageReportRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DamageReportServiceImpl(DamageReportRepository damageReportRepository, ModelMapper modelMapper) {
        this.damageReportRepository = damageReportRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<DamageReportDTO>> findAll() {
        List<DamageReportEntity> damageReportEntities = damageReportRepository.findAll();
        List<DamageReportDTO> damageReportDTOS = damageReportEntities.stream()
                .map(damageReport -> modelMapper.map(damageReport, DamageReportDTO.class))
                .toList();
        ResponseEntity<List<DamageReportDTO>> responseEntity = ResponseEntity.ok().body(damageReportDTOS);
        return responseEntity;
    }
}
