package com.gideon.todolist.infrastructure.web.controllers;


import com.gideon.todolist.infrastructure.web.models.ApiResponseJSON;
import com.gideon.todolist.infrastructure.web.models.TaskCreationRequestJSON;
import com.gideon.todolist.infrastructure.web.models.TaskUpdateRequestJSON;
import com.gideon.todolist.usecase.TaskUseCase;
import com.gideon.todolist.usecase.data.requests.TaskUpdateRequest;
import com.gideon.todolist.usecase.data.responses.GetTaskResponse;
import com.gideon.todolist.usecase.data.responses.TaskUpdateResponse;
import com.gideon.todolist.usecase.models.TaskModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Task Endpoints", description = "Handles task records")
@RestController
@RequestMapping(value = "/api/v1/tasks")
public class TaskController {

    TaskUseCase taskUseCase;

    public TaskController(TaskUseCase taskUseCase) {
        this.taskUseCase = taskUseCase;
    }

    @ApiOperation(value = "Creates Task")
    @PostMapping(value = "")
    public ResponseEntity<ApiResponseJSON<TaskModel>> createTask(@RequestBody TaskCreationRequestJSON requestJSON){
        TaskModel taskModel = taskUseCase.createTask(requestJSON.toRequest());
        return new ResponseEntity<>(new ApiResponseJSON<>("Processed Successfully", taskModel), HttpStatus.OK);
    }

    @ApiOperation(value = "Gets Tasks")
    @GetMapping(value = "")
    public ResponseEntity<List<GetTaskResponse>> getTasks(){
        List<GetTaskResponse> taskResponses = taskUseCase.getTasks();
        return new ResponseEntity<>(taskResponses, HttpStatus.OK);
    }

    @ApiOperation(value = "Gets Task by Id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<GetTaskResponse> getTaskById(@PathVariable Long id){
        GetTaskResponse response = taskUseCase.getTaskById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Updates Task as done")
    @PostMapping(value = "/{taskId}/update-task-status")
    public ResponseEntity<ApiResponseJSON<TaskUpdateResponse>> updateTaskAsDone(@PathVariable("taskId") Long id,
                                                               @RequestBody TaskUpdateRequestJSON requestJSON){
        TaskUpdateResponse response = taskUseCase.markTaskAsDoneOrUndone(id, requestJSON.toRequest());
        return new ResponseEntity<>(new ApiResponseJSON<>("Updated As Done Successfully", response), HttpStatus.OK);
    }
}
