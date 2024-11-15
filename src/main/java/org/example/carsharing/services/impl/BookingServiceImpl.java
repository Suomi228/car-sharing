package org.example.carsharing.services.impl;

import org.example.carsharing.dto.BookingDTO;
import org.example.carsharing.models.*;
import org.example.carsharing.repositories.*;
import org.example.carsharing.services.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, ModelMapper modelMapper) {
        this.bookingRepository = bookingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<BookingDTO>> findAll() {
        List<BookingEntity> bookingEntities = bookingRepository.findAll();
        List<BookingDTO> bookingDTOS = bookingEntities.stream()
                .map(booking -> modelMapper.map(booking, BookingDTO.class))
                .toList();
        ResponseEntity<List<BookingDTO>> responseEntity = ResponseEntity.ok().body(bookingDTOS);
        return responseEntity;
    }

    @Override
    public ResponseEntity<List<BookingDTO>> findByCustomerId(Long customerId) {
        List<BookingEntity> bookingEntities = bookingRepository.findByCustomerId(customerId);
        List<BookingDTO> bookingDTOS = bookingEntities.stream()
                .map(booking -> modelMapper.map(booking, BookingDTO.class))
                .toList();
        ResponseEntity<List<BookingDTO>> responseEntity = ResponseEntity.ok().body(bookingDTOS);
        return responseEntity;
    }
}
