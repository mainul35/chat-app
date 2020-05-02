package com.mainul35.chatapp.controller;

import com.mainul35.chatapp.service.RegistrationService;
import com.mainul35.chatapp.viewmodel.Registration;
import com.mainul35.chatapp.viewmodel.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegistrationController {

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registration", new Registration());
        return "register";
    }

    @GetMapping("/public/email-sent")
    public String emailSent(HttpServletRequest httpServletRequest) {
        return "email_sent";
    }
}

@RestController
class RegistrationRestController {

    private final RegistrationService registrationService;

    RegistrationRestController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping(value = "/register", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ResponseObject> registrationHandler(@RequestBody Registration registration) {
        registrationService.registerUser(registration);
        return new ResponseEntity<ResponseObject>(new ResponseObject(null, "Success"), HttpStatus.OK);
    }
}
