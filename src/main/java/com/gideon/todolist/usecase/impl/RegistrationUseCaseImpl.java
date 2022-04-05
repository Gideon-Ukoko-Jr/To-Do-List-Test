package com.gideon.todolist.usecase.impl;

import com.gideon.todolist.domain.dao.UserEntityDao;
import com.gideon.todolist.domain.entities.UserEntity;
import com.gideon.todolist.events.RegistrationCompleteEvent;
import com.gideon.todolist.usecase.RegistrationUseCase;
import com.gideon.todolist.usecase.data.requests.RegistrationRequest;
import com.gideon.todolist.usecase.exceptions.BadRequestException;
import com.gideon.todolist.utils.EmailUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Named;

@Named
@AllArgsConstructor
public class RegistrationUseCaseImpl implements RegistrationUseCase {

    private final UserEntityDao userEntityDao;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher publisher;


    @Override
    public void signUp(RegistrationRequest registrationRequest) {

        UserEntity user = new UserEntity();

        if (userEntityDao.existByUsername(registrationRequest.getUsername())){
            throw new BadRequestException("Username already in use");
        }

        if (userEntityDao.existByEmail(registrationRequest.getEmail())){
            throw new BadRequestException("Username already in use");
        }

        if (!EmailUtils.isValid(registrationRequest.getEmail())){
            throw new BadRequestException("Invalid email");
        }

        user.setUsername(registrationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setEmail(registrationRequest.getEmail());

        userEntityDao.saveRecord(user);

//        publisher.publishEvent(new RegistrationCompleteEvent(user, "url"));
    }
}
