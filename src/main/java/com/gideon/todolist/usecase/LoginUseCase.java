package com.gideon.todolist.usecase;

import com.gideon.todolist.usecase.data.requests.LoginRequest;
import com.gideon.todolist.usecase.data.responses.AuthenticationResponse;

public interface LoginUseCase {

    AuthenticationResponse login(LoginRequest loginRequest);
}
