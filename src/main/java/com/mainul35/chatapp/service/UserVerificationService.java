package com.mainul35.chatapp.service;

import com.mainul35.chatapp.entity.security.AuthUser;
import com.mainul35.chatapp.entity.verification.UserVerification;
import com.mainul35.chatapp.enums.VerificationGatewayType;
import com.mainul35.chatapp.enums.VerificationType;
import com.mainul35.chatapp.repository.AuthRepository;
import com.mainul35.chatapp.repository.UserVerificationRepository;
import com.mainul35.chatapp.viewmodel.MailVerification;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserVerificationService {

    private final JavaMailSender mailSender;
    private final AuthRepository authRepository;
    private final UserVerificationRepository userVerificationRepository;

    public UserVerificationService(JavaMailSender mailSender, AuthRepository authRepository, UserVerificationRepository userVerificationRepository) {
        this.mailSender = mailSender;
        this.authRepository = authRepository;
        this.userVerificationRepository = userVerificationRepository;
    }

    public void generateAndSendActivationLink(AuthUser user) {
        sendVerificationCodeToEmail(user.getEmail(), generateVerificationCode(user));
    }

    public void receiveVerificationCode(MailVerification mailVerification) {
        AuthUser user = authRepository.findByEmailIgnoreCase(mailVerification.getEmail()).get();
        Optional<UserVerification> userVerification = userVerificationRepository
                .findByUserAndVerificationCodeAndVerificationGatewayType(user, mailVerification.getVerificationCode(),
                                                                        VerificationGatewayType.EMAIL);
        if(userVerification.isPresent() && userVerification.get().getValidityTime().isAfter(LocalDateTime.now())) {
            user.setEnabled(true);
            authRepository.save(user);
        }
    }

    public UserVerification generateVerificationCode(AuthUser user) {
        UserVerification verification = new UserVerification();
        verification.setVerificationGatewayType(VerificationGatewayType.EMAIL);
        verification.setVerificationCode(ThreadLocalRandom.current().nextInt(100000, 1000000));
        verification.setValidityTime(LocalDateTime.now().plusDays(3));
        verification.setGeneratedAt(LocalDateTime.now());
        verification.setVerificationType(VerificationType.ACCOUNT_ACTIVATION);
        verification.setUser(user);
//                UserVerification.builder()
//                        .verificationGatewayType(VerificationGatewayType.EMAIL)
//                        .verificationCode(ThreadLocalRandom.current().nextInt(100000, 1000000))
//                        .validityTime(LocalDateTime.now().plusDays(3))
//                        .generatedAt(LocalDateTime.now())
//                        .verificationType(VerificationType.ACCOUNT_ACTIVATION)
//                        .user(user).build();
        return userVerificationRepository.save(verification);
    }

    public void sendVerificationCodeToEmail(String email, UserVerification userVerification) {
        // TODO: use email template
        String text = "Your verification code: " + userVerification.getVerificationCode();
        sendMail(email, "Verification Code", text);
    }

    public void sendMail(String mailTo, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailTo);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
