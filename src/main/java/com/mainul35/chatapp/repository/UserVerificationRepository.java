package com.mainul35.chatapp.repository;

import com.mainul35.chatapp.entity.security.AuthUser;
import com.mainul35.chatapp.entity.verification.UserVerification;
import com.mainul35.chatapp.enums.VerificationGatewayType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserVerificationRepository extends JpaRepository<UserVerification, Long> {
    Optional<UserVerification> findByUserAndVerificationCodeAndVerificationGatewayType(AuthUser user,
                                                                                       Integer verificationCode,
                                                                                       VerificationGatewayType gatewayType);
}
