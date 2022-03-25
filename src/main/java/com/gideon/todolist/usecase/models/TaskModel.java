package com.gideon.todolist.usecase.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskModel {

    long id;
    private String task;
    private String dueDate;
    private String creator;
}
