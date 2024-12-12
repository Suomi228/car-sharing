package org.example.carsharing.controllers;

import org.example.carsharing.constants.CarClass;
import org.example.carsharing.constants.CarStatus;
import org.example.carsharing.dto.CarDTO;
import org.example.carsharing.services.CarService;
import org.example.carsharingcontracts.controllers.AdminController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminControllerImpl implements AdminController {
    private final CarService carService;

    @Autowired
    public AdminControllerImpl(CarService carService) {
        this.carService = carService;
    }
    @GetMapping("/get")
    @Override
    public String getAllCars(@RequestParam(value = "carClass", required = false) String carClass, @RequestParam(value = "carStatus", required = false) String carStatus, Model model) {
        List<CarDTO> filteredCars;

        if ((carClass == null || carClass.isEmpty()) && (carStatus == null || carStatus.isEmpty())) {
            filteredCars = carService.findAll().getBody();
        } else {
            try {
                if (carClass != null && !carClass.isEmpty() && (carStatus == null || carStatus.isEmpty())) {
                    CarClass carClassEnum = CarClass.valueOf(carClass.toUpperCase());
                    filteredCars = carService.getAllCarsByCarClass(carClassEnum).getBody();
                }
                else if (carStatus != null && !carStatus.isEmpty() && (carClass == null || carClass.isEmpty())) {
                    CarStatus carStatusEnum = CarStatus.valueOf(carStatus.toUpperCase());
                    filteredCars = carService.getAllCarsByStatus(carStatusEnum).getBody();
                }
                else {
                    CarClass carClassEnum = CarClass.valueOf(carClass.toUpperCase());
                    CarStatus carStatusEnum = CarStatus.valueOf(carStatus.toUpperCase());
                    List<CarDTO> carsByClass = carService.getAllCarsByCarClass(carClassEnum).getBody();
                    filteredCars = carsByClass.stream()
                            .filter(car -> car.getStatus().equals(carStatusEnum))
                            .toList();
                }
            } catch (IllegalArgumentException e) {
                filteredCars = List.of();
            }
        }
        model.addAttribute("allCars", filteredCars);
        model.addAttribute("carClasses", CarClass.values());
        model.addAttribute("carStatuses", CarStatus.values());
        return "adminCars";
    }
    @GetMapping("/editCar/{id}")
    @Override
    public String editCar(@PathVariable("id") Long id, Model model) {
        CarDTO carDTO = carService.getCar(id);
        model.addAttribute("car", carDTO);
        model.addAttribute("carClasses", CarClass.values());
        model.addAttribute("carStatuses", CarStatus.values());
        return "editCar";
    }
    @PostMapping("/updateCar")
//    @Override
    public String updateCar(CarDTO carDTO) {
        carService.updateCar(carDTO);
        return "redirect:/admin/get";
    }



    @PostMapping("/deleteCar/{id}")
    @Override
    public String deleteCar(@PathVariable("id") Long id) {
        carService.deleteCar(id);
        return "redirect:/admin/get";
    }
}
