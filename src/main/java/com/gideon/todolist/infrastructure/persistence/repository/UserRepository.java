package com.gideon.todolist.infrastructure.persistence.repository;

import com.gideon.todolist.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    @Query("select user from UserEntity user where user.username = ?1")
    UserEntity getUserEntityByUsername(String username);

    UserEntity findOneByUsername(String username);
}
