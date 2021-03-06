package com.gideon.todolist.infrastructure.persistence.daoImpl;

import com.gideon.todolist.domain.dao.UserEntityDao;
import com.gideon.todolist.domain.entities.UserEntity;
import com.gideon.todolist.infrastructure.persistence.repository.UserRepository;

import javax.inject.Named;
import java.util.Optional;

@Named
public class UserEntityDaoImpl implements UserEntityDao {

    private UserRepository userRepository;

    public UserEntityDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserEntity> findById(Long aLong) {
        return userRepository.findById(aLong);
    }

    @Override
    public UserEntity getRecordById(Long aLong) throws RuntimeException {
        return userRepository.findById(aLong).orElseThrow(() -> new RuntimeException("Not Found. UserEntity with id: "+aLong ));
    }

    @Override
    public UserEntity saveRecord(UserEntity record) {
        return userRepository.save(record);
    }

    @Override
    public Optional<UserEntity> findUserByUsername(String username) {
//        return userRepository.getUserEntityByUsername(username);
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
