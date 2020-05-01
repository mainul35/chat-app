package com.mainul35.chatapp.exception;

import com.mainul35.chatapp.service.UserVerificationService;
import com.mainul35.chatapp.service.auth.AuthService;

public class UserNotActiveException extends RuntimeException {



    public UserNotActiveException(String message) {
        super(message);
    }
}
