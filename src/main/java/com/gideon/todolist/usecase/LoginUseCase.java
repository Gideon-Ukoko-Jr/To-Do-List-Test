package com.gideon.todolist.usecase;

import com.gideon.todolist.usecase.data.requests.LoginRequest;

public interface LoginUseCase {

    String login(LoginRequest loginRequest);
}
