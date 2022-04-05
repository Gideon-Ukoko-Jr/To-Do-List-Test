package com.gideon.todolist.usecase.impl;

import com.gideon.todolist.domain.dao.TaskEntityDao;
import com.gideon.todolist.domain.dao.UserEntityDao;
import com.gideon.todolist.domain.entities.TaskEntity;
import com.gideon.todolist.domain.entities.UserEntity;
import com.gideon.todolist.infrastructure.web.security.services.UserDetailsImpl;
import com.gideon.todolist.usecase.TaskUseCase;
import com.gideon.todolist.usecase.data.requests.TaskCreationRequest;
import com.gideon.todolist.usecase.data.responses.GetTaskResponse;
import com.gideon.todolist.usecase.exceptions.BadRequestException;
import com.gideon.todolist.usecase.models.TaskModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.inject.Named;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Named
@AllArgsConstructor
@Slf4j
public class TaskUseCaseImpl implements TaskUseCase {

    private final TaskEntityDao taskEntityDao;
    private final UserEntityDao userEntityDao;

    @Override
    public TaskModel createTask(TaskCreationRequest request) {
        String task = request.getTask();
        LocalDate dueDate = request.getDueDate();

        String currentUserName = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }

        UserEntity user = userEntityDao.findUserByUsername(currentUserName).orElseThrow(() -> new BadRequestException("User doesn't in the system"));

        if(request.getDueDate() == null){
            dueDate = null;
        }

        TaskEntity taskEntity = TaskEntity.builder()
                .taskName(task)
                .dueDate(dueDate)
                .done(false)
                .overdue(false)
                .user(user)
                .build();

        taskEntityDao.saveRecord(taskEntity);

        long id = taskEntity.getId();


        TaskModel taskResponse = TaskModel.builder()
                .id(id)
                .task(task)
                .creator(currentUserName)
                .build();
        if(dueDate != null){
            taskResponse.setDueDate(dueDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
        return taskResponse;
    }

    @Override
    public List<GetTaskResponse> getTasks() {
        return taskEntityDao.getTasks().stream().map(this::fromTaskEntityToGetTaskResponse).collect(Collectors.toList());
    }

    @Override
    public GetTaskResponse getTaskById(long id) {
        TaskEntity taskEntity = taskEntityDao.getRecordById(id);

        if (taskEntity.isOverdue()){
            // Throw a Flag to signify it's overdue
        }

        return fromTaskEntityToGetTaskResponse(taskEntity);
    }

    @Override
    public void setTasksAsOverdue() {
        List<TaskEntity> tasks = taskEntityDao.getOverdueTasks();
        if (tasks.isEmpty()){
            return;
        }
        log.info("Tasks that are overdue - {}", tasks.size());

        for (TaskEntity task : tasks){
            task.setOverdue(true);
        }

//        List<TaskEntity> taskEntities = taskEntityDao.getTasks();
//        for (TaskEntity task : taskEntities){
//
//            String dateRightNow = String.valueOf(LocalDate.now());
//            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
//            LocalDate formattedDateRightNow = LocalDate.parse(dateRightNow, formatter);
//
//            if (formattedDateRightNow.isAfter(task.getDueDate())){
//                task.setOverdue(true);
//            }
//        }
    }

    public GetTaskResponse fromTaskEntityToGetTaskResponse(TaskEntity taskEntity){

        return GetTaskResponse.builder()
                .id(taskEntity.getId())
                .task(taskEntity.getTaskName())
                .dueDate(String.valueOf(taskEntity.getDueDate()))
                .creator(taskEntity.getUser().getUsername())
                .done(taskEntity.isDone())
                .overdue(taskEntity.isOverdue())
                .build();
    }
}
