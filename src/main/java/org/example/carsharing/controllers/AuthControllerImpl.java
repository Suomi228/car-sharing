package org.example.carsharing.controllers;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger LOG = LogManager.getLogger(Controller.class);

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
    public String register(@ModelAttribute @Valid SignupInputModel signupInputModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("signupInputModel", signupInputModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.signupInputModel", bindingResult);
            logSample("POST", "register", signupInputModel.getNumber(), "Ошибки в биндинге");
            return "redirect:/auth/register";
        }

        try {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setFirstName(signupInputModel.getFirstName());
            customerDTO.setLastName(signupInputModel.getLastName());
            customerDTO.setNumber(signupInputModel.getNumber());
            customerDTO.setPassword(signupInputModel.getPassword());
            customerDTO.setAdmin(false);

            customerService.registerCustomer(customerDTO, signupInputModel.getPassword());
            logSample("POST", "register", signupInputModel.getNumber(), "Зарегистрировался");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("signupInputModel", signupInputModel);
            redirectAttributes.addFlashAttribute("errorMessage", "Номер уже используется.");
            logSample("POST", "register", signupInputModel.getNumber(), "Номер телефона уже используется.");
            return "redirect:/auth/register";
        }

        return "redirect:/auth/login";
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
        logSample("POST", "onFailedLogin", number, "Не удалось войти");
        return "redirect:/auth/login";
    }

    private void logSample(String requestType, String methodName, String username, String message) {
        LOG.log(Level.INFO, "Request Time: {}, Request Type: {}, Method: {}, Username: {}, Message: {}",
                java.time.LocalDateTime.now(), requestType, methodName, username, message);
    }
}
