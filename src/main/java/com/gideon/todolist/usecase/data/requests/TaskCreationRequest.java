package com.gideon.todolist.usecase.data.requests;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TaskCreationRequest {

    private String task;
    private LocalDate dueDate;

}
