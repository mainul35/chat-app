package com.mainul35.chatapp.service.auth;

import com.mainul35.chatapp.exception.UserNotFoundException;
import com.mainul35.chatapp.repository.AuthRepository;
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

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var authUser = authRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("no.user.found.with.this.username"));
        return authUser;
    }
}
