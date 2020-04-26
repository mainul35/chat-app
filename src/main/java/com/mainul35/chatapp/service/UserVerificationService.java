package com.mainul35.chatapp.service;

import com.mainul35.chatapp.entity.security.AuthUser;
import com.mainul35.chatapp.entity.verification.UserVerification;
import com.mainul35.chatapp.entity.verification.VerificationGatewayType;
import com.mainul35.chatapp.entity.verification.VerificationType;
import com.mainul35.chatapp.repository.UserVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserVerificationService {

    @Autowired
    private UserVerificationRepository userVerificationRepository;

    public void sendRandomCodeToEmail(AuthUser user) {
        UserVerification verification = new UserVerification();
        verification.setVerificationGatewayType(VerificationGatewayType.EMAIL);
        verification.setVerificationCode(ThreadLocalRandom.current().nextInt(100000, 1000000));
        verification.setValidityTime(LocalDateTime.now().plusDays(3));
        verification.setGeneratedAt(LocalDateTime.now());
        verification.setVerificationType(VerificationType.ACCOUNT_ACTIVATION);
        verification.setUser(user);
        userVerificationRepository.save(verification);
    }
}
