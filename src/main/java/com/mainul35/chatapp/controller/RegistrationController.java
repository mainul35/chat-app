package com.mainul35.chatapp.controller;

import com.mainul35.chatapp.viewmodel.Registration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registration", new Registration());
        return "register";
    }

    @PostMapping("/register")
    public String registrationHandler(Model model, @ModelAttribute Registration registration) {
        System.out.println(registration.toString());
        return "";
    }
}
