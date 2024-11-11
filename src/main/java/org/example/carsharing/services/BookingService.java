package org.example.carsharing.services;

import org.example.carsharing.dto.BookingDTO;
import org.example.carsharing.models.BookingEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookingService {
    BookingDTO createBooking(BookingDTO bookingDTO);
    BookingDTO getBookingById(Long bookingId);
    BookingDTO updateBookingStatus(Long bookingId, String status);
    List<BookingDTO> getBookingsByCustomerId(Long customerId);
    boolean isCarAvailable(Long carId, String startDate, String endDate);
}
