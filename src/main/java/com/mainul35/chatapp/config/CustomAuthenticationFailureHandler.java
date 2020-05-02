package com.mainul35.chatapp.config;

import com.mainul35.chatapp.repository.AuthRepository;
import com.mainul35.chatapp.service.UserVerificationService;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final AuthRepository authRepository;

    private final UserVerificationService verificationService;

    public CustomAuthenticationFailureHandler(UserVerificationService verificationService, AuthRepository authRepository) {
        this.verificationService = verificationService;
        this.authRepository = authRepository;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException, ServletException {
        if(e.getMessage().equals("user_inactive")) {
            String email = httpServletRequest.getParameter("email");
            verificationService.generateAndSendActivationLink(authRepository.findByEmailIgnoreCase(email).get());
            httpServletResponse.sendRedirect("/public/email-sent");
        }
    }
}
