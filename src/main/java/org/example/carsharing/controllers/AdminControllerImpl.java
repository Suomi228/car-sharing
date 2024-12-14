package org.example.carsharing.controllers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminControllerImpl implements AdminController {
    private final CarService carService;
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    @Autowired
    public AdminControllerImpl(CarService carService) {
        this.carService = carService;
    }
    @GetMapping("/get")
    @Override
    public String getAllCars(@RequestParam(value = "carClass", required = false) String carClass, @RequestParam(value = "carStatus", required = false) String carStatus, Model model, Principal principal) {
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
        logSample("GET", "getAllCars", principal.getName(), "Страница для админов");
        return "adminCars";
    }
    @GetMapping("/editCar/{id}")
    @Override
    public String editCar(@PathVariable("id") Long id, Model model, Principal principal) {
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
        logSample("GET", "editCar", principal.getName(), "Страница редактирования машины");
        return "editCar";
    }

    @GetMapping("/create")
    @Override
    public String showCreateCarForm(Model model, Principal principal) {
        model.addAttribute("carInputModel", new CarInputModel());
        model.addAttribute("carClasses", CarClass.values());
        model.addAttribute("statuses", CarStatus.values());
        logSample("GET", "showCreateCarForm", principal.getName(), "Страница создания машины");
        return "createCar";
    }

    @PostMapping("/create")
    @Override
    public String createCar(@ModelAttribute CarInputModel carInputModel, Principal principal) {
        try {
            CarDTO carDTO = new CarDTO();
            carDTO.setName(carInputModel.getName());
            carDTO.setYear(carInputModel.getYear());
            carDTO.setNumber(carInputModel.getNumber());
            carDTO.setCarClass(CarClass.valueOf(carInputModel.getCarClass().toUpperCase()));
            carDTO.setHourPrice(carInputModel.getHourPrice());
            carDTO.setStatus(CarStatus.valueOf(carInputModel.getStatus().toUpperCase()));
            carDTO.setAdress(carInputModel.getAdress());

            carService.createCar(carDTO);
            logSample("POST", "createCar", principal.getName(), "Машина: " + carInputModel.getName() + " создана");
        } catch (Exception e) {
            logSample("POST", "createCar", principal.getName(), e.getMessage());
        }
        return "redirect:/car/create";
    }

    @PostMapping("/updateCar")
    @Override
    public String updateCar(@ModelAttribute CarInputModel carInputModel, Principal principal) {
        try {
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
            logSample("POST", "updateCar", principal.getName(), "Машина: " + carInputModel.getId() + " обновлена");
        } catch (Exception e) {
            logSample("POST", "updateCar", principal.getName(), e.getMessage());
        }
        return "redirect:/admin/get";
    }

    @PostMapping("/deleteCar/{id}")
    @Override
    public String deleteCar(@PathVariable("id") Long id, Principal principal) {
        try {
            carService.deleteCar(id);
            logSample("POST", "deleteCar", principal.getName(), "Машина: " +  id + " удалена");
        } catch (Exception e) {
            logSample("POST", "deleteCar", principal.getName(), e.getMessage());
        }
        return "redirect:/admin/get";
    }
    private void logSample(String requestType, String methodName, String username, String message) {
        LOG.log(Level.INFO, "Request Time: {}, Request Type: {}, Method: {}, Username: {}, Message: {}",
                java.time.LocalDateTime.now(), requestType, methodName, username, message);
    }
}
