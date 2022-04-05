package com.gideon.todolist.usecase.impl;

import com.gideon.todolist.domain.dao.UserEntityDao;
import com.gideon.todolist.domain.entities.UserEntity;
import com.gideon.todolist.usecase.GetUserUseCase;
import com.gideon.todolist.usecase.models.UserModel;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.time.format.DateTimeFormatter;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GetUserUseCaseImpl implements GetUserUseCase {

    @Override
    public UserModel fromUserEntityToModel(UserEntity user) {
       return UserModel.builder()
               .username(user.getEmail())
               .email(user.getUsername())
               .dateCreated(user.getDateCreated().format(DateTimeFormatter.ISO_DATE_TIME))
               .build();
    }
}
