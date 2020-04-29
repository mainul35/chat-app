package com.mainul35.chatapp.service;

import com.mainul35.chatapp.entity.security.AuthUser;
import com.mainul35.chatapp.entity.verification.UserVerification;
import com.mainul35.chatapp.entity.enums.VerificationGatewayType;
import com.mainul35.chatapp.entity.enums.VerificationType;
import com.mainul35.chatapp.repository.UserVerificationRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserVerificationService {

    private final JavaMailSender mailSender;
    private final UserVerificationRepository userVerificationRepository;

    public UserVerificationService(JavaMailSender mailSender, UserVerificationRepository userVerificationRepository) {
        this.mailSender = mailSender;
        this.userVerificationRepository = userVerificationRepository;
    }

    public void sendRandomCodeToEmail(AuthUser user) {
        UserVerification verification =
                UserVerification.builder()
                                .verificationGatewayType(VerificationGatewayType.EMAIL)
                                .verificationCode(ThreadLocalRandom.current().nextInt(100000, 1000000))
                                .validityTime(LocalDateTime.now().plusDays(3))
                                .generatedAt(LocalDateTime.now())
                                .verificationType(VerificationType.ACCOUNT_ACTIVATION)
                                .user(user).build();
        UserVerification savedVerification = userVerificationRepository.save(verification);

        // TODO: use email template
        String text = "Your verification code: " + savedVerification.getVerificationCode();
        sendMail(user.getEmail(), "Verification Code", text);
    }

    public void sendMail(String mailTo, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailTo);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
