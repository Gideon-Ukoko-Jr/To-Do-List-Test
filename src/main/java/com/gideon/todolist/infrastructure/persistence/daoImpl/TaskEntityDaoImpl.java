package com.gideon.todolist.infrastructure.persistence.daoImpl;

import com.gideon.todolist.domain.dao.TaskEntityDao;
import com.gideon.todolist.domain.entities.TaskEntity;
import com.gideon.todolist.infrastructure.persistence.repository.TaskRepository;

import javax.inject.Named;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Override
    public List<TaskEntity> getOverdueTasks() {
        return taskRepository.getOverdueTasks(LocalDate.now());
    }

    @Override
    public List<TaskEntity> getAllTasksByUser(String username) {
        return taskRepository.getAllTasksByUser(username);
    }

    @Override
    public List<TaskEntity> getTasksDueTomorrow() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        return taskRepository.getTasksDueTomorrow(today, tomorrow);
    }

    @Override
    public List<TaskEntity> getTasksDueToday() {
        LocalDate today = LocalDate.now();
        return taskRepository.getTasksDueToday(today);
    }

}
