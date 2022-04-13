package com.gideon.todolist.usecase.data.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskUpdateRequest {
    private String done;
}
