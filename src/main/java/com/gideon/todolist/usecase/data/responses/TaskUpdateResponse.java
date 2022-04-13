package com.gideon.todolist.usecase.data.responses;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class TaskUpdateResponse {

    private String task;
    private LocalDate dueDate;
    private boolean done;
    private LocalDateTime dateTimeUpdated;
}
