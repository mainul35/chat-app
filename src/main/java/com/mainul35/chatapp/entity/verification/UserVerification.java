package com.mainul35.chatapp.entity.verification;

import com.mainul35.chatapp.entity.BaseId;
import com.mainul35.chatapp.entity.security.AuthUser;
import com.mainul35.chatapp.enums.VerificationGatewayType;
import com.mainul35.chatapp.enums.VerificationType;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
public class UserVerification extends BaseId {
    private VerificationGatewayType verificationGatewayType;
    private Integer verificationCode;
    private LocalDateTime validityTime;
    private LocalDateTime generatedAt;
    private VerificationType verificationType;
    @ManyToOne
    private AuthUser user;
}
