package org.example.carsharing.controllers;

import org.example.carsharing.constants.CarClass;
import org.example.carsharing.constants.CarStatus;
import org.example.carsharing.dto.CarDTO;
import org.example.carsharing.services.CarService;
import org.example.carsharingcontracts.controllers.AdminController;
import org.example.carsharingcontracts.input.CarInputModel;
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
            filteredCars = carService.findAll();
        } else {
            try {
                if (carClass != null && !carClass.isEmpty() && (carStatus == null || carStatus.isEmpty())) {
                    CarClass carClassEnum = CarClass.valueOf(carClass.toUpperCase());
                    filteredCars = carService.getAllCarsByCarClass(carClassEnum);
                }
                else if (carStatus != null && !carStatus.isEmpty() && (carClass == null || carClass.isEmpty())) {
                    CarStatus carStatusEnum = CarStatus.valueOf(carStatus.toUpperCase());
                    filteredCars = carService.getAllCarsByStatus(carStatusEnum);
                }
                else {
                    CarClass carClassEnum = CarClass.valueOf(carClass.toUpperCase());
                    CarStatus carStatusEnum = CarStatus.valueOf(carStatus.toUpperCase());
                    List<CarDTO> carsByClass = carService.getAllCarsByCarClass(carClassEnum);
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
        CarDTO car = carService.getCar(id);
        CarInputModel carInputModel = new CarInputModel();
        carInputModel.setId(car.getId());
        carInputModel.setName(car.getName());
        carInputModel.setYear(car.getYear());
        carInputModel.setNumber(car.getNumber());
        carInputModel.setCarClass(car.getCarClass().name());
        carInputModel.setHourPrice(car.getHourPrice());
        carInputModel.setStatus(car.getStatus().name());
        carInputModel.setAdress(car.getAdress());

        model.addAttribute("carInputModel", carInputModel);
        model.addAttribute("carClasses", CarClass.values());
        model.addAttribute("carStatuses", CarStatus.values());
        return "editCar";
    }

    @PostMapping("/updateCar")
    @Override
    public String updateCar(@ModelAttribute CarInputModel carInputModel) {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(carInputModel.getId());
        carDTO.setName(carInputModel.getName());
        carDTO.setYear(carInputModel.getYear());
        carDTO.setNumber(carInputModel.getNumber());
        carDTO.setCarClass(CarClass.valueOf(carInputModel.getCarClass().toUpperCase()));
        carDTO.setHourPrice(carInputModel.getHourPrice());
        carDTO.setStatus(CarStatus.valueOf(carInputModel.getStatus().toUpperCase()));
        carDTO.setAdress(carInputModel.getAdress());

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
