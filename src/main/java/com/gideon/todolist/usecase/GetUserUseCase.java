package com.gideon.todolist.usecase;

import com.gideon.todolist.domain.entities.UserEntity;
import com.gideon.todolist.usecase.models.UserModel;

public interface GetUserUseCase {

    UserModel fromUserEntityToModel(UserEntity user);
}
