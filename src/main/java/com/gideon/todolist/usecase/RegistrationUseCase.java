package com.gideon.todolist.usecase;

import com.gideon.todolist.domain.entities.UserEntity;
import com.gideon.todolist.usecase.data.requests.RegistrationRequest;

public interface RegistrationUseCase {

    void signUp(RegistrationRequest registrationRequest);
}
