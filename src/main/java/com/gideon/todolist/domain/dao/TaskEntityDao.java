package com.gideon.todolist.domain.dao;

import com.gideon.todolist.domain.entities.TaskEntity;

import java.util.List;

public interface TaskEntityDao extends CrudDao<TaskEntity, Long>{

    List<TaskEntity> getTasks();

    long getNumberOfTasks();
}
