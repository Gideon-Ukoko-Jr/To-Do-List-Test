package com.gideon.todolist.usecase.impl;

import com.gideon.todolist.domain.dao.TaskEntityDao;
import com.gideon.todolist.domain.dao.UserEntityDao;
import com.gideon.todolist.domain.entities.TaskEntity;
import com.gideon.todolist.domain.entities.UserEntity;
import com.gideon.todolist.infrastructure.web.security.services.UserDetailsImpl;
import com.gideon.todolist.service.EmailSenderService;
import com.gideon.todolist.usecase.TaskUseCase;
import com.gideon.todolist.usecase.data.requests.TaskCreationRequest;
import com.gideon.todolist.usecase.data.requests.TaskUpdateRequest;
import com.gideon.todolist.usecase.data.responses.GetTaskResponse;
import com.gideon.todolist.usecase.data.responses.TaskUpdateResponse;
import com.gideon.todolist.usecase.exceptions.BadRequestException;
import com.gideon.todolist.usecase.exceptions.TODOException;
import com.gideon.todolist.usecase.models.TaskModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.inject.Named;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Named
@AllArgsConstructor
@Slf4j
public class TaskUseCaseImpl implements TaskUseCase {

    private final TaskEntityDao taskEntityDao;
    private final UserEntityDao userEntityDao;
    private EmailSenderService emailSenderService;

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
            throw new TODOException("Task is overdue -> Task Name : " + taskEntity.getTaskName() + " Task Due Date : " + taskEntity.getDueDate());
        }

        return fromTaskEntityToGetTaskResponse(taskEntity);
    }

    @Override
    public TaskUpdateResponse markTaskAsDoneOrUndone(long taskId, TaskUpdateRequest request) {
        TaskEntity taskEntity = taskEntityDao.getRecordById(taskId);

        String currentUserName = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }

        if (!Objects.equals(taskEntity.getUser().getUsername(), currentUserName)){
            throw new BadRequestException("You are not authorized to update task");
        }

        String doneUpdate = request.getDone();

        boolean done = taskEntity.isDone();

        if (doneUpdate.equals("yes")){
            done = true;
        }

        if (doneUpdate.equals("no")){
            done = false;
        }

        taskEntity.setDone(done);

        taskEntityDao.saveRecord(taskEntity);

        LocalDate dueDate = taskEntity.getDueDate();
        String task = taskEntity.getTaskName();
        LocalDateTime dateTimeModified = taskEntity.getDateModified();

        return TaskUpdateResponse.builder()
                .task(task)
                .dueDate(dueDate)
                .done(done)
                .dateTimeUpdated(dateTimeModified)
                .build();
    }

    @Override
    public List<GetTaskResponse> getTasksForUser() {

        String currentUserName = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }

        UserEntity user = userEntityDao.findUserByUsername(currentUserName).orElseThrow(() -> new BadRequestException("User doesn't exist in the system"));

        return taskEntityDao.getAllTasksByUser(user.getUsername()).stream().map(this::fromTaskEntityToGetTaskResponse).collect(Collectors.toList());
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
            taskEntityDao.saveRecord(task);
        }

    }

    @Override
    public void sendAlmostDueEmail() throws MessagingException, UnsupportedEncodingException {

        String subject = "Task Due In Ten Minutes";
        List<TaskEntity> tasks = taskEntityDao.getTasks();
        if (tasks.isEmpty()){
            return;
        }

        for (TaskEntity task : tasks){
            if (task.getDueDate().getMonth() == LocalDate.now().getMonth()){
                if (task.getDueDate().getDayOfMonth() == LocalDate.now().getDayOfMonth() + 1){
                    emailSenderService.sendMail(task.getUser().getUsername(), task.getUser().getEmail(), subject, task.getTaskName());
                }
            }
        }
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
