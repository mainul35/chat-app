package com.mainul35.chatapp.service.auth;

import com.mainul35.chatapp.entity.security.AuthUser;
import com.mainul35.chatapp.exception.UserNotActiveException;
import com.mainul35.chatapp.exception.UserNotFoundException;
import com.mainul35.chatapp.repository.AuthRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        var userOptional = authRepository.findByEmailIgnoreCase(username);
        userOptional = userOptional.isPresent() ? userOptional : authRepository.findByUsernameIgnoreCase(username);
        var user = userOptional.orElseThrow(() -> new UserNotFoundException("no.user.found.with.this.username"));
        if (user.isEnabled()) {
            return user;
        }
        //TODO: Add localization code
        throw  new UserNotActiveException("user.not.active");
    }

    public AuthUser getLoggedInUser() {
        var user = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return authRepository.findByEmailIgnoreCase(user.getEmail()).get();
    }
}
