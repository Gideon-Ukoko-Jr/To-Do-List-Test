package com.gideon.todolist.usecase.impl;

import com.gideon.todolist.infrastucture.web.security.JwtProvider;
import com.gideon.todolist.usecase.LoginUseCase;
import com.gideon.todolist.usecase.data.requests.LoginRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.inject.Named;

@Named
public class LoginUseCaseImpl implements LoginUseCase {

    private AuthenticationManager authenticationManager;
    private JwtProvider tokenProvider;

    public LoginUseCaseImpl(AuthenticationManager authenticationManager, JwtProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public String login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.generateToken(authentication);
    }
}
