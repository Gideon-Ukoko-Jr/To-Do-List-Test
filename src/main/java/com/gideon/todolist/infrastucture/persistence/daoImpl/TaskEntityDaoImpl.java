package com.gideon.todolist.infrastucture.persistence.daoImpl;

import com.gideon.todolist.domain.dao.TaskEntityDao;
import com.gideon.todolist.domain.entities.TaskEntity;
import com.gideon.todolist.infrastucture.persistence.repository.TaskRepository;

import javax.inject.Named;
import java.util.List;
import java.util.Optional;

@Named
public class TaskEntityDaoImpl implements TaskEntityDao {

    private TaskRepository taskRepository;

    public TaskEntityDaoImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<TaskEntity> findById(Long aLong) {
        return taskRepository.findById(aLong);
    }

    @Override
    public TaskEntity getRecordById(Long aLong) throws RuntimeException {
        return taskRepository.findById(aLong).orElseThrow(() -> new RuntimeException("Not Found. TaskEntity with id: "+aLong ));
    }

    @Override
    public TaskEntity saveRecord(TaskEntity record) {
        return taskRepository.save(record);
    }

    @Override
    public List<TaskEntity> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public long getNumberOfTasks() {
        return taskRepository.count();
    }
}
