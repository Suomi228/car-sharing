package org.example.carsharing.services;

import org.example.carsharing.dto.DamageReportDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface DamageReportService {
    ResponseEntity<List<DamageReportDTO>> findAll();
}
