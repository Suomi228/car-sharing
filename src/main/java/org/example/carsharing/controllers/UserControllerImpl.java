package org.example.carsharing.controllers;

import org.example.carsharing.dto.CustomerDTO;
import org.example.carsharing.repositories.CustomerRepository;
import org.example.carsharing.services.CustomerService;
import org.example.carsharingcontracts.viewModel.OneTripModel;
import org.example.carsharingcontracts.viewModel.ReturnCarModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.example.carsharing.dto.RentInfoDto;
import org.example.carsharing.services.BookingService;
import org.example.carsharingcontracts.controllers.UserController;
import org.example.carsharingcontracts.viewModel.BaseViewModel;
import org.example.carsharingcontracts.viewModel.MyTripsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

    private final BookingService bookingService;
    private final CustomerService customerService;

    @Autowired
    public UserControllerImpl(BookingService bookingService, CustomerService customerService) {
        this.bookingService = bookingService;
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    @Override
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
    public String getCars() {
        return "";
    }

    @Override
    public String getCarsByClass(String carClass) {
        return "";
    }

    @Override
    public void rentCar(Long userId, Long carId) {

    }

    @Override
    public void returnCar(ReturnCarModel returnCarModel) {

    }
}
