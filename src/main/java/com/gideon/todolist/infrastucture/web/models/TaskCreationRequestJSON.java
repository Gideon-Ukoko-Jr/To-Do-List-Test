package com.gideon.todolist.infrastucture.web.models;

import com.gideon.todolist.usecase.data.requests.TaskCreationRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@Builder
public class TaskCreationRequestJSON {

    @ApiModelProperty(notes = "Task to be done", required = true)
    @NotEmpty
    @NotNull
    private String task;

    @ApiModelProperty(notes = "End date Format: dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String dueDate;


    public TaskCreationRequest toRequest(){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        if (dueDate != null){
            LocalDate dDate = LocalDate.parse(dueDate, formatter);
            return TaskCreationRequest.builder()
                    .task(task)
                    .dueDate(dDate)
                    .build();
        }

        return TaskCreationRequest.builder()
                .task(task)
                .build();
    }
}
