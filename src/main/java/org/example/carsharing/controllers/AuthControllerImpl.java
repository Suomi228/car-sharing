package org.example.carsharing.controllers;

import jakarta.validation.Valid;
import org.example.carsharing.dto.CustomerDTO;
import org.example.carsharing.services.CustomerService;
import org.example.carsharingcontracts.controllers.AuthController;
import org.example.carsharingcontracts.input.LoginInputModel;
import org.example.carsharingcontracts.input.SignupInputModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthControllerImpl implements AuthController {
    private final CustomerService customerService;

    public AuthControllerImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("signupInputModel", new SignupInputModel());
        return "register";
    }

    @PostMapping("/register")
    @Override
    public String register(@ModelAttribute @Valid SignupInputModel signupInputModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(signupInputModel.getFirstName());
        customerDTO.setLastName(signupInputModel.getLastName());
        customerDTO.setNumber(signupInputModel.getNumber());
        customerDTO.setPassword(signupInputModel.getPassword());
        customerDTO.setAdmin(false);

        customerService.registerCustomer(customerDTO, signupInputModel.getPassword());
        return "redirect:/user/homePage";
    }

    @GetMapping("/login")
    @Override
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    @Override
    public String onFailedLogin(@ModelAttribute("number") String number, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("number", number);
        redirectAttributes.addFlashAttribute("badCredentials", true);
        return "redirect:/users/login";
    }
}
