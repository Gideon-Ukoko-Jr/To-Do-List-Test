package com.gideon.todolist.domain.dao;

import com.gideon.todolist.domain.entities.UserEntity;

public interface UserEntityDao extends CrudDao<UserEntity, Long>{

    boolean existByUsername(String username);
    boolean existByEmail(String email);
}
