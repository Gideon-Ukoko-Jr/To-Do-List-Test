package com.gideon.todolist.usecase;

import com.gideon.todolist.usecase.data.requests.TaskCreationRequest;
import com.gideon.todolist.usecase.data.requests.TaskUpdateRequest;
import com.gideon.todolist.usecase.data.responses.GetTaskResponse;
import com.gideon.todolist.usecase.data.responses.TaskUpdateResponse;
import com.gideon.todolist.usecase.models.TaskModel;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface TaskUseCase {

    TaskModel createTask(TaskCreationRequest request);
    List<GetTaskResponse> getTasks();
    GetTaskResponse getTaskById(long id);

    TaskUpdateResponse markTaskAsDoneOrUndone(long taskId, TaskUpdateRequest request);

    List<GetTaskResponse> getTasksForUser();

    void setTasksAsOverdue();

    void sendDueInTenMinutesMail() throws MessagingException, UnsupportedEncodingException;
    void sendDueInAnHourMail() throws MessagingException, UnsupportedEncodingException;
    void sendOverdueByTenMinutesMail() throws MessagingException, UnsupportedEncodingException;
    void sendOverdueByAnHourMail() throws MessagingException, UnsupportedEncodingException;
}
