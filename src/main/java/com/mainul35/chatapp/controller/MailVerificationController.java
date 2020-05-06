package com.mainul35.chatapp.controller;

import com.mainul35.chatapp.service.UserVerificationService;
import com.mainul35.chatapp.util.Patterns;
import com.mainul35.chatapp.viewmodel.CodeVerification;
import com.mainul35.chatapp.viewmodel.MailVerification;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/public")
public class MailVerificationController {

    private final UserVerificationService userVerificationService;

    public MailVerificationController(UserVerificationService userVerificationService) {
        this.userVerificationService = userVerificationService;
    }

    @GetMapping("/email-sent")
    public String emailSent(HttpServletRequest httpServletRequest) {
        return "email_sent";
    }

    @PostMapping("/submit-code")
    public String receiveCode(@Valid @ModelAttribute CodeVerification codeVerification) {
        userVerificationService.receiveVerificationCode(codeVerification);
        return "redirect:/public/verification-success";
    }

    @GetMapping("/verification-success")
    public String verificationSuccessful() {
        return "verificationSuccess";
    }

    @PostMapping("/re-initiate-verification")
    public String reInitiateVerification(@Valid @ModelAttribute MailVerification verification) throws BindException {
        userVerificationService.generateAndSendActivationLink(verification.getEmail());
        return "redirect:/public/email-sent";
    }

    @GetMapping("/verification-failure")
    public String verificationFailed() {
        return "verificationFailed";
    }
}
