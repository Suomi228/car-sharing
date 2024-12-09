package org.example.carsharing.controllers;

import org.example.carsharing.constants.CarClass;
import org.example.carsharing.constants.CarStatus;
import org.example.carsharing.dto.CarDTO;
import org.example.carsharing.services.CarService;
import org.example.carsharingcontracts.controllers.CrudCarController;
import org.example.carsharingcontracts.input.CarInputModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/car")
public class CrudCarControllerImpl implements CrudCarController {

    private final CarService carService;

    public CrudCarControllerImpl(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/create")
    @Override
    public String showCreateCarForm(Model model) {
        model.addAttribute("carInputModel", new CarInputModel());
        model.addAttribute("carClasses", CarClass.values());
        model.addAttribute("statuses", CarStatus.values());
        return "createCar";
    }

    @PostMapping("/create")
    @Override
    public String createCar(@ModelAttribute CarInputModel carInputModel) {
        CarDTO carDTO = new CarDTO();
        carDTO.setName(carInputModel.getName());
        carDTO.setYear(carInputModel.getYear());
        carDTO.setNumber(carInputModel.getNumber());
        carDTO.setCarClass(CarClass.valueOf(carInputModel.getCarClass().toUpperCase()));
        carDTO.setHourPrice(carInputModel.getHourPrice());
        carDTO.setStatus(CarStatus.valueOf(carInputModel.getStatus().toUpperCase()));
        carDTO.setAdress(carInputModel.getAdress());

        System.out.println(carDTO + "blablabla");
        carService.createCar(carDTO);
        return "redirect:/car/create";
    }

    @Override
    public void editCar(CarInputModel carInputModel) {

    }

    @Override
    public void deleteCar(Long carId) {

    }
}
