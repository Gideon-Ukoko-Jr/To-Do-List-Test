package com.gideon.todolist.usecase.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskModel {

    private String task;
    private String dueDate;
}
