package com.mainul35.chatapp.service;

import com.mainul35.chatapp.entity.security.AuthUser;
import com.mainul35.chatapp.entity.security.Role;
import com.mainul35.chatapp.exception.UserAlreadyRegisteredException;
import com.mainul35.chatapp.repository.RegistrationRepository;
import com.mainul35.chatapp.viewmodel.Registration;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    private final PasswordEncoder passwordEncoder;

    public RegistrationService(RegistrationRepository registrationRepository, PasswordEncoder passwordEncoder) {
        this.registrationRepository = registrationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(Registration registration) {
        var user = this.buildUser(registration);
        registrationRepository.save(user);
    }

    private AuthUser buildUser(Registration registration) {
        var userOptional = registrationRepository.findByEmail(registration.getEmail());
        userOptional.ifPresent(authUser -> {
            throw new UserAlreadyRegisteredException("user.already.registered");
        });
        var user = new AuthUser();
        BeanUtils.copyProperties(registration, user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        return user;
    }
}
