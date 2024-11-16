package org.example.carsharing.controllers;

import org.example.carsharing.dto.BookingDTO;
import org.example.carsharing.dto.RentInfoDto;
import org.example.carsharing.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<List<BookingDTO>> getBookings() {
        ResponseEntity<List<BookingDTO>> responseEntity;
        responseEntity = bookingService.findAll();
        return responseEntity;
    }
    @GetMapping(path = "/get/{id}")
    public ResponseEntity<List<RentInfoDto>> getBookingsById(@PathVariable("id") Long id) {
        ResponseEntity<List<RentInfoDto>> responseEntity;
        responseEntity = bookingService.findByCustomerId(id);
        return responseEntity;
    }

}