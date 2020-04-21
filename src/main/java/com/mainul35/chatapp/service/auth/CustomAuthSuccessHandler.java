package com.mainul35.chatapp.service.auth;

import com.mainul35.chatapp.entity.security.AuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(CustomAuthSuccessHandler.class);
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Authentication authentication
    ) throws IOException, ServletException {
        HttpSession session = httpServletRequest.getSession();
        AuthUser authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //set our response to OK status
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        //since we have created our custom success handler, its up to us to where
        //we will redirect the user after successfully login
        SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(httpServletRequest, httpServletResponse);
//        String requestUrl = savedRequest.getRedirectUrl();
        httpServletResponse.sendRedirect("/home"); //requestUrl!=null?requestUrl:
    }
}
