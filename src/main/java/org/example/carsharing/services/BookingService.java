package org.example.carsharing.services;

import org.example.carsharing.dto.BookingDTO;
import org.example.carsharing.dto.RentInfoDto;
import org.example.carsharing.dto.UnfinishedBookingDTO;
import org.example.carsharing.models.BookingEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookingService {
    List<BookingDTO> findAll();
    List<RentInfoDto> findTripsById(Long customerId);
    List<UnfinishedBookingDTO> findUnfinishedBookings(String number);
}
