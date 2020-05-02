package com.mainul35.chatapp.controller;

import com.mainul35.chatapp.service.UserVerificationService;
import com.mainul35.chatapp.viewmodel.MailVerification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MailVerificationController {

    private final UserVerificationService userVerificationService;

    public MailVerificationController(UserVerificationService userVerificationService) {
        this.userVerificationService = userVerificationService;
    }

    @PostMapping("/public/submit-code")
    public String receiveCode(@ModelAttribute MailVerification mailVerification) {
        userVerificationService.receiveVerificationCode(mailVerification);
        return "redirect:/public/verification-success";
    }

    @GetMapping("/public/verification-success")
    public String verificationSuccessful() {
        return "verificationSuccess";
    }

    @GetMapping("/public/verification-failure")
    public String verificationFailed() {
        return "verificationFailed";
    }
}
