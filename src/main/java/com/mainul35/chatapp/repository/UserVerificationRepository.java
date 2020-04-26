package com.mainul35.chatapp.repository;

import com.mainul35.chatapp.entity.verification.UserVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVerificationRepository extends JpaRepository<UserVerification, Long> {
}
