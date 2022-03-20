package com.gideon.todolist.infrastucture.web.controllers;

import com.gideon.todolist.infrastucture.web.models.LoginRequestJSON;
import com.gideon.todolist.infrastucture.web.models.RegistrationRequestJSON;
import com.gideon.todolist.usecase.LoginUseCase;
import com.gideon.todolist.usecase.RegistrationUseCase;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Authentication Endpoints", description = "Handles authentication")
@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final RegistrationUseCase registrationUseCase;

    public AuthController(LoginUseCase loginUseCase, RegistrationUseCase registrationUseCase) {
        this.loginUseCase = loginUseCase;
        this.registrationUseCase = registrationUseCase;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody RegistrationRequestJSON requestJSON){
//        authService.signUp(requestJSON.toRequest());
        registrationUseCase.signUp(requestJSON.toRequest());
        return new ResponseEntity<>("User Registration Successful", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestJSON requestJSON){
//        authService.login(requestJSON.toRequest());
        loginUseCase.login(requestJSON.toRequest());
        return new ResponseEntity<>("User Login Successful", HttpStatus.OK);
    }
}
