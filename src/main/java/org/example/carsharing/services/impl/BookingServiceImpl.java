package org.example.carsharing.services.impl;

import org.example.carsharing.dto.BookingDTO;
import org.example.carsharing.models.*;
import org.example.carsharing.repositories.*;
import org.example.carsharing.services.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, CarRepository carRepository, CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.bookingRepository = bookingRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        boolean available = isCarAvailable(bookingDTO.getCarId(), bookingDTO.getStartDate(), bookingDTO.getEndDate());
        if (!available) {
            throw new IllegalArgumentException("Автомобиль недоступен в выбранные даты.");
        }
        CarEntity car = carRepository.findById(bookingDTO.getCarId());
        CustomerEntity customer = customerRepository.findById(bookingDTO.getCustomerId());

        BookingEntity booking = modelMapper.map(bookingDTO, BookingEntity.class);
        booking.setCar(car);
        booking.setCustomer(customer);

        bookingRepository.save(booking);

        bookingDTO.setId(booking.getId());
        return bookingDTO;
    }

    @Override
    public BookingDTO getBookingById(Long bookingId) {
        BookingEntity booking = bookingRepository.findById(bookingId);
        return modelMapper.map(booking, BookingDTO.class);
    }


    @Override
    public BookingDTO updateBookingStatus(Long bookingId, String status) {
        BookingEntity booking = bookingRepository.findById(bookingId);

        booking.setStatus(status);
        bookingRepository.save(booking);

        return modelMapper.map(booking, BookingDTO.class);
    }

    @Override
    public List<BookingDTO> getBookingsByCustomerId(Long customerId) {
        return bookingRepository.findByCustomerId(customerId).stream()
                .map(booking -> modelMapper.map(booking, BookingDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isCarAvailable(Long carId, String startDate, String endDate) {
        List<BookingEntity> bookings = bookingRepository.findByCarIdAndStatus(carId, "CONFIRMED");
        for (BookingEntity booking : bookings) {
            if (datesOverlap(startDate, endDate, booking.getStartDate(), booking.getEndDate())) {
                return false;
            }
        }
        return true;
    }

    private boolean datesOverlap(String start1, String end1, String start2, String end2) {
        return !(end1.compareTo(start2) < 0 || start1.compareTo(end2) > 0);
    }

}
