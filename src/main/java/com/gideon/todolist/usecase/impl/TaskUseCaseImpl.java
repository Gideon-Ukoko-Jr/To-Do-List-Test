package com.gideon.todolist.usecase.impl;

import com.gideon.todolist.domain.dao.TaskEntityDao;
import com.gideon.todolist.domain.entities.TaskEntity;
import com.gideon.todolist.usecase.TaskUseCase;
import com.gideon.todolist.usecase.data.requests.TaskCreationRequest;
import com.gideon.todolist.usecase.data.responses.GetTaskResponse;
import com.gideon.todolist.usecase.models.TaskModel;

import javax.inject.Named;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class TaskUseCaseImpl implements TaskUseCase {

    private final TaskEntityDao taskEntityDao;

    public TaskUseCaseImpl(TaskEntityDao taskEntityDao) {
        this.taskEntityDao = taskEntityDao;
    }

    @Override
    public TaskModel createTask(TaskCreationRequest request) {
        String task = request.getTask();
        LocalDate dueDate = request.getDueDate();

        if(request.getDueDate() == null){
            dueDate = null;
        }

        TaskEntity taskEntity = TaskEntity.builder()
                .taskName(task)
                .dueDate(dueDate)
                .done(false)
                .build();

        taskEntityDao.saveRecord(taskEntity);

        if (dueDate != null){
            return TaskModel.builder()
                    .task(task)
                    .dueDate(dueDate.format(DateTimeFormatter.ISO_LOCAL_DATE))
                    .build();
        }

        return TaskModel.builder()
                .task(task)
                .build();
    }

    @Override
    public List<GetTaskResponse> getTasks() {
        return taskEntityDao.getTasks().stream().map(this::fromTaskEntityToGetTaskResponse).collect(Collectors.toList());
    }

    public GetTaskResponse fromTaskEntityToGetTaskResponse(TaskEntity taskEntity){

        return GetTaskResponse.builder()
                .task(taskEntity.getTaskName())
                .dueDate(String.valueOf(taskEntity.getDueDate()))
                .done(taskEntity.isDone())
                .build();
    }
}
