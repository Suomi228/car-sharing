package org.example.carsharing.services.impl;

import org.example.carsharing.constants.BookingStatus;
import org.example.carsharing.constants.CarClass;
import org.example.carsharing.constants.CarStatus;
import org.example.carsharing.constants.PaymentStatus;
import org.example.carsharing.dto.BookingDTO;
import org.example.carsharing.dto.CarDTO;
import org.example.carsharing.models.BookingEntity;
import org.example.carsharing.models.CarEntity;
import org.example.carsharing.models.CustomerEntity;
import org.example.carsharing.models.PaymentEntity;
import org.example.carsharing.repositories.BookingRepository;
import org.example.carsharing.repositories.CarRepository;
import org.example.carsharing.repositories.CustomerRepository;
import org.example.carsharing.repositories.PaymentRepository;
import org.example.carsharing.services.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, CustomerRepository customerRepository, BookingRepository bookingRepository, PaymentRepository paymentRepository) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public ResponseEntity<List<CarDTO>> findAll() {
        List<CarEntity> carEntities = carRepository.findAll();
        List<CarDTO> carDTOS = carEntities.stream()
                .map(car -> modelMapper.map(car, CarDTO.class))
                .toList();
        ResponseEntity<List<CarDTO>> responseEntity = ResponseEntity.ok().body(carDTOS);
        return responseEntity;
    }

    @Override
    public ResponseEntity<CarDTO> updateStatus(Long id, CarStatus carStatus) {
        CarEntity carEntity = carRepository.findById(id);
        carEntity.changeStatus(carStatus);
        CarEntity savedOrderEntity = carRepository.save(carEntity);
        CarDTO carDTO = modelMapper.map(savedOrderEntity, CarDTO.class);
        ResponseEntity<CarDTO> responseEntity = ResponseEntity.ok().body(carDTO);
        return responseEntity;
    }
    @Override
    public ResponseEntity<BookingDTO> rentCar(Long customerId, Long carId) {
        System.out.println("customer" + customerId + " carId " + carId);
        CarEntity car = carRepository.findById(carId);
        if (car == null) {
            throw new RuntimeException("No such carId");
        }

        CustomerEntity customer = customerRepository.findById(customerId);
        if (customer == null) {
            throw new RuntimeException("No such customerId");
        }

        if (car.getStatus() == CarStatus.USED) {
            throw new RuntimeException("Car is already rented");
        }

        car.changeStatus(CarStatus.USED);
        carRepository.save(car);

        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        BookingEntity booking = new BookingEntity();
        booking.setCar(car);
        booking.setCustomer(customer);
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setStartDate(formattedDate);
        bookingRepository.save(booking);
        BookingDTO bookingDTO = modelMapper.map(booking, BookingDTO.class);
        bookingDTO.setCarId(car.getId());
        bookingDTO.setCustomerId(customer.getId());
        ResponseEntity<BookingDTO> responseEntity = ResponseEntity.ok().body(bookingDTO);
        return responseEntity;
    }

    @Override
    public ResponseEntity<BookingDTO> returnCar(Long carId, Long bookingId, String carAdress) {
        CarEntity car = carRepository.findById(carId);

        BookingEntity booking = bookingRepository.findById(bookingId);

        if (!(booking.getCar().getId() == (carId))) {
            throw new IllegalArgumentException("No such booking with this car.");
        }

        car.setStatus(CarStatus.FREE);
        car.setAdress(carAdress);
        carRepository.save(car);

        LocalDateTime endDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        booking.setEndDate(endDate.format(formatter));
        booking.setStatus(BookingStatus.DONE);
        bookingRepository.save(booking);

        LocalDateTime startDate = LocalDateTime.parse(booking.getStartDate(), formatter);
        long hours = ChronoUnit.HOURS.between(startDate, endDate);
        if (hours == 0) {
            hours = 1; // Минимальная стоимость за один час
        }
        double totalPrice = car.getHourPrice() * hours;
        PaymentEntity payment = new PaymentEntity();
        payment.setBooking(booking);
        payment.setTotalPrice(totalPrice);
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        payment.setPaymentDate(endDate.format(formatter));
        paymentRepository.save(payment);

        BookingDTO bookingDTO = modelMapper.map(booking, BookingDTO.class);
        ResponseEntity<BookingDTO> responseEntity = ResponseEntity.ok().body(bookingDTO);
        return responseEntity;
    }

    @Override
    public ResponseEntity<List<CarDTO>> getFreeCars() {
        List<CarEntity> freeCars = carRepository.findByStatus(CarStatus.FREE);
        List<CarDTO> carDTOS = freeCars.stream()
                .map(car -> modelMapper.map(car, CarDTO.class))
                .toList();
        ResponseEntity<List<CarDTO>> responseEntity = ResponseEntity.ok().body(carDTOS);
        return responseEntity;
    }

    @Override
    public ResponseEntity<List<CarDTO>> findByCarClass(CarClass carClass) {
        List<CarEntity> freeCars = carRepository.findByCarClassAndStatus(carClass, CarStatus.FREE);
        List<CarDTO> carDTOS = freeCars.stream()
                .map(car -> modelMapper.map(car, CarDTO.class))
                .toList();
        ResponseEntity<List<CarDTO>> responseEntity = ResponseEntity.ok().body(carDTOS);
        return responseEntity;
    }
}
