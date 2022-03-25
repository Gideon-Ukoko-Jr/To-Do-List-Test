package com.gideon.todolist.domain.dao;

import com.gideon.todolist.domain.entities.UserEntity;

import java.util.Optional;

public interface UserEntityDao extends CrudDao<UserEntity, Long>{

    Optional<UserEntity> findUserByUsername(String username);
    boolean existByUsername(String username);
    boolean existByEmail(String email);
}
