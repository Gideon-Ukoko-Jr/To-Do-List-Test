package com.gideon.todolist.usecase.data.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetTaskResponse {

    private long id;
    private String task;
    private String dueDate;
    private String creator;
    private boolean done;
    private boolean overdue;
}
