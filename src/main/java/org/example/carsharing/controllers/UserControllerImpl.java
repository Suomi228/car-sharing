package org.example.carsharing.controllers;

import org.example.carsharing.constants.CarClass;
import org.example.carsharing.dto.*;
import org.example.carsharing.repositories.CustomerRepository;
import org.example.carsharing.services.CarService;
import org.example.carsharing.services.CustomerService;
import org.example.carsharingcontracts.input.AdressInputModel;
import org.example.carsharingcontracts.viewModel.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.example.carsharing.services.BookingService;
import org.example.carsharingcontracts.controllers.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

    private final BookingService bookingService;
    private final CustomerService customerService;
    private final CarService carService;

    @Autowired
    public UserControllerImpl(BookingService bookingService, CustomerService customerService, CarService carService) {
        this.bookingService = bookingService;
        this.customerService = customerService;
        this.carService = carService;
    }

    @Override
    @GetMapping("/{id}")
    public String getMyTrips(@PathVariable Long id, Model model) {
        List<RentInfoDto> trips = bookingService.findByCustomerId(id);
        CustomerDTO customerDTO = customerService.findById(id);
        String fullName = customerDTO.getFirstName() + " " + customerDTO.getLastName();
        List<OneTripModel> tripModels = trips.stream()
                .map(trip -> new OneTripModel(
                        trip.getRentId(),
                        trip.getStartDate(),
                        trip.getEndDate(),
                        trip.getCarName(),
                        trip.getTotalPrice()
                ))
                .toList();

        BaseViewModel base = new BaseViewModel("Мои поездки", fullName);
        MyTripsModel myTripsModel = new MyTripsModel(base, tripModels);
        model.addAttribute("myTrips", myTripsModel);

        return "myTrips";
    }
    @Override
    @GetMapping("/homePage")
    public String homePage(@RequestParam(value = "carClass", required = false) String carClass, Model model) {
        List<CarDTO> freeCars;
        if (carClass != null && carClass.isEmpty()) {
            return "redirect:/user/homePage";
        }
        if (carClass != null && !carClass.isEmpty()) {
            try {
                CarClass carClassEnum = CarClass.valueOf(carClass.toUpperCase());
                freeCars = carService.getFreeCarsByCarClass(carClassEnum).getBody();
            } catch (IllegalArgumentException e) {
                freeCars = List.of();
            }
        } else {
            freeCars = carService.getFreeCars();
        }

        model.addAttribute("freeCars", freeCars);
        model.addAttribute("carClasses", CarClass.values());
        return "home";
    }

    @PostMapping("/rentCar")
    @Override
    public String rentCar(@RequestParam Long customerId, @RequestParam Long carId, RedirectAttributes redirectAttributes) {
        try {
            ResponseEntity<BookingDTO> bookingDTOResponseEntity = carService.rentCar(customerId, carId);
            redirectAttributes.addFlashAttribute("successMessage", "Машина успешно арендована!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/user/homePage";
    }

    @GetMapping("/{userId}/returnCar")
    @Override
    public String returnCarPage(@PathVariable Long userId, Model model) {
        List<UnfinishedBookingDTO> unfinishedBookings = bookingService.findUnfinishedBookings(userId);
        System.out.println(unfinishedBookings);
        if (unfinishedBookings == null || unfinishedBookings.isEmpty()) {
            model.addAttribute("errorMessage", "У вас нет незавершенных заказов.");
            return "returnCar";
        }

        AdressInputModel addressInput = new AdressInputModel();
        addressInput.setAdress("");

        List<ReturnCarModel> returnCarModels = unfinishedBookings.stream()
                .map(booking -> new ReturnCarModel(
                        new BaseViewModel("Незавершенные заказы", "User Full Name"),
                        booking.getCarId(),
                        booking.getBookingId(),
                        booking.getStartDate(),
                        booking.getCarName(),
                        addressInput
                ))
                .toList();

        ReturnCarListModel returnCarListModel = new ReturnCarListModel(
                new BaseViewModel("Незавершенные заказы", "User Full Name"),
                returnCarModels
        );
        System.out.println(returnCarListModel);

        model.addAttribute("returnCarList", returnCarListModel);
        return "returnCar";
    }

    @PostMapping("/returnCar")

    public String returnCar(@ModelAttribute CarReturnRequestDTO requestDTO, RedirectAttributes redirectAttributes) {
        try {
            carService.returnCar(requestDTO.getCarId(), requestDTO.getBookingId(), requestDTO.getCarAddress());
            redirectAttributes.addFlashAttribute("successMessage", "Машина успешно возвращена!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ошибка: " + e.getMessage());
        }
        return "redirect:/user/1/returnCar";
    }
}
