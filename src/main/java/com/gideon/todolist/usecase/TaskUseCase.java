package com.gideon.todolist.usecase;

import com.gideon.todolist.usecase.data.requests.TaskCreationRequest;
import com.gideon.todolist.usecase.data.responses.GetTaskResponse;
import com.gideon.todolist.usecase.models.TaskModel;

import java.util.List;

public interface TaskUseCase {

    TaskModel createTask(TaskCreationRequest request);
    List<GetTaskResponse> getTasks();
    GetTaskResponse getTaskById(long id);

    void setTasksAsOverdue();
}
