package com.gideon.todolist.infrastucture.persistence.repository;

import com.gideon.todolist.domain.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

}
