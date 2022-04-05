package com.gideon.todolist.usecase.impl;

import com.gideon.todolist.domain.dao.UserEntityDao;
import com.gideon.todolist.domain.entities.UserEntity;
import com.gideon.todolist.infrastructure.web.security.jwt.JwtUtils;
import com.gideon.todolist.infrastructure.web.security.services.UserDetailsImpl;
import com.gideon.todolist.usecase.GetUserUseCase;
import com.gideon.todolist.usecase.LoginUseCase;
import com.gideon.todolist.usecase.data.requests.LoginRequest;
import com.gideon.todolist.usecase.data.responses.AuthenticationResponse;
import com.gideon.todolist.usecase.exceptions.BadRequestException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Named;

@Named
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class LoginUseCaseImpl implements LoginUseCase {

//    private final AuthenticationManager authenticationManager;
//    private final JwtProvider tokenProvider;

    AuthenticationManager authenticationManager;
    UserEntityDao userEntityDao;
    PasswordEncoder passwordEncoder;
    JwtUtils jwtUtils;


    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {
//
//        String authenticationToken = tokenProvider.generateToken(authentication);
//
//        return new AuthenticationResponse(authenticationToken, loginRequest.getUsername());

        String username = loginRequest.getUsername();
        UserEntity user = userEntityDao.findUserByUsername(username).orElseThrow(() -> new BadRequestException("Invalid email or password."));

        if(StringUtils.isEmpty(user.getPassword())){
            throw new BadRequestException("Sorry, login detail has not been setup.");
        }
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid email or password.");
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return AuthenticationResponse.builder()
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .token(jwt)
                .type("Bearer")
                .build();
    }

}
