package com.gideon.todolist.domain.entities;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
public class TaskEntity extends AbstractBaseEntity<Long>{

    @Column(nullable = false)
    private String taskName;

    private LocalDate dueDate;

    @Column(nullable = false)
    private boolean done = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false, name = "app_user_id")
    private UserEntity user;

    @Column(nullable = false)
    private boolean overdue = false;
}
