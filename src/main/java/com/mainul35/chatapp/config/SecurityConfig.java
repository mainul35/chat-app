package com.mainul35.chatapp.config;

import com.mainul35.chatapp.service.CustomAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC_ENDPOINTS = {
            "/",
            "/login",
            "/register",
            "/stream",
            "/resources/**",
            "/js/**",
            "/css/**",
            "/img/**",
            "/endpoint/**",
            "/chat.**",
            "/topic/**",
            "/temp/**"
    };

    private final CustomAuthProvider customAuthProvider;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    public SecurityConfig(CustomAuthProvider customAuthProvider, AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.customAuthProvider = customAuthProvider;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(PUBLIC_ENDPOINTS).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login-processing")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(authenticationSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthProvider);
    }
}
