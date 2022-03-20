package com.gideon.todolist.domain.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_table")
public class UserEntity extends AbstractBaseEntity<Long>{

    @Column(nullable = false)
    private String username;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String role;

//    private Instant created;
//
//    private boolean enabled;
}
