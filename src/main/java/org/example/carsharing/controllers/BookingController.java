package org.example.carsharing.controllers;

import org.example.carsharing.dto.BookingDTO;
import org.example.carsharing.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    // Создание нового бронирования
//    @PostMapping
//    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
//        BookingDTO createdBooking = bookingService.createBooking(bookingDTO);
//        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
//    }

    // Получение бронирования по ID
    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id) {
        BookingDTO booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }

    // Обновление статуса бронирования
    @PutMapping("/{id}/status")
    public ResponseEntity<BookingDTO> updateBookingStatus(@PathVariable Long id, @RequestParam String status) {
        BookingDTO updatedBooking = bookingService.updateBookingStatus(id, status);
        return ResponseEntity.ok(updatedBooking);
    }

    // Получение всех бронирований по ID клиента
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<BookingDTO>> getBookingsByCustomerId(@PathVariable Long customerId) {
        List<BookingDTO> bookings = bookingService.getBookingsByCustomerId(customerId);
        return ResponseEntity.ok(bookings);
    }

    // Проверка доступности автомобиля на указанные даты
    @GetMapping("/availability")
    public ResponseEntity<Boolean> isCarAvailable(@RequestParam Long carId, @RequestParam String startDate, @RequestParam String endDate) {
        boolean available = bookingService.isCarAvailable(carId, startDate, endDate);
        return ResponseEntity.ok(available);
    }
}