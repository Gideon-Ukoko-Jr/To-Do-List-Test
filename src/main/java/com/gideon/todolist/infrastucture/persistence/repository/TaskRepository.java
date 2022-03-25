package com.gideon.todolist.infrastucture.persistence.repository;

import com.gideon.todolist.domain.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {


    @Query("select t from TaskEntity t where t.recordStatus = com.gideon.todolist.domain.enums.RecordStatusConstant.ACTIVE and " +
            "t.done = false and t.dueDate >= current_date")
    List<TaskEntity> getOverdueTasks();
}
