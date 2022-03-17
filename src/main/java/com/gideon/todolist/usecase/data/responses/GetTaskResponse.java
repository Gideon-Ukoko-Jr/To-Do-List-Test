package com.gideon.todolist.usecase.data.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetTaskResponse {

    private String task;
    private String dueDate;
    private boolean done;
}
