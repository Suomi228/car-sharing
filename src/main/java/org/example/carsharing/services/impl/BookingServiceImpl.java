package org.example.carsharing.services.impl;

import org.example.carsharing.dto.BookingDTO;
import org.example.carsharing.dto.RentInfoDto;
import org.example.carsharing.dto.UnfinishedBookingDTO;
import org.example.carsharing.models.*;
import org.example.carsharing.repositories.*;
import org.example.carsharing.services.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@EnableCaching
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;
    private final PaymentRepository paymentRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, ModelMapper modelMapper, PaymentRepository paymentRepository) {
        this.bookingRepository = bookingRepository;
        this.modelMapper = modelMapper;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<BookingDTO> findAll() {
        List<BookingEntity> bookingEntities = bookingRepository.findAll();
        List<BookingDTO> bookingDTOS = bookingEntities.stream()
                .map(booking -> modelMapper.map(booking, BookingDTO.class))
                .toList();
        return bookingDTOS;
    }

    @Override
    @Cacheable("myTrips")
    public List<RentInfoDto> findTripsById(Long customerId) {
        List<BookingEntity> bookingEntities = bookingRepository.findByCustomerId(customerId);
        List<RentInfoDto> bookingInfoDTOS = bookingEntities.stream().map(booking -> {
            RentInfoDto dto = new RentInfoDto();
            dto.setRentId(booking.getId());
            dto.setCarName(booking.getCar().getName());
            dto.setStartDate(booking.getStartDate());
            dto.setEndDate(booking.getEndDate());
            PaymentEntity payment = paymentRepository.findByBookingId(booking.getId());
            if (payment != null){
                dto.setTotalPrice(payment.getTotalPrice());
            }
            return dto;
        }).toList();
        return bookingInfoDTOS;
    }

    @Override
    public List<UnfinishedBookingDTO> findUnfinishedBookings(String number) {
        List<BookingEntity> unfinishedBookings = bookingRepository.findByCustomerNumberAndEndDateIsNull(number);
        return unfinishedBookings.stream()
                .map(booking -> {
                    UnfinishedBookingDTO dto = new UnfinishedBookingDTO();
                    dto.setCarId(booking.getCar().getId());
                    dto.setBookingId(booking.getId());
                    dto.setStartDate(booking.getStartDate().toString());
                    dto.setCarName(booking.getCar().getName());
                    dto.setCarAddress(booking.getCar().getAdress());
                    return dto;
                })
                .toList();
    }
}
