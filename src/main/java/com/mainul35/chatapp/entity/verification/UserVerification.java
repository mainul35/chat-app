package com.mainul35.chatapp.entity.verification;

import com.mainul35.chatapp.entity.security.AuthUser;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Data
public class UserVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private VerificationGatewayType verificationGatewayType;
    private Integer verificationCode;
    private LocalDateTime validityTime;
    private LocalDateTime generatedAt;
    private VerificationType verificationType;
    @ManyToOne
    private AuthUser user;
}
