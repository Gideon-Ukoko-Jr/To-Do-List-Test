package com.gideon.todolist.domain.entities;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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

}
