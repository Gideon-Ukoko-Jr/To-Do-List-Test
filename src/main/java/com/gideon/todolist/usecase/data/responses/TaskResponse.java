package com.gideon.todolist.usecase.data.responses;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TaskResponse {

    private String task;
    private LocalDate dueDate;
}
