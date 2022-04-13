package com.gideon.todolist.infrastructure.web.models;

import com.gideon.todolist.usecase.data.requests.TaskCreationRequest;
import com.gideon.todolist.usecase.data.requests.TaskUpdateRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class TaskUpdateRequestJSON {

    @ApiModelProperty(notes = "yes | no", required = true)
    @Pattern(regexp = "(yes|no)")
    @NotEmpty
    @NotNull
    private String done;


    public TaskUpdateRequest toRequest(){

        return TaskUpdateRequest.builder()
                .done(done)
                .build();
    }
}
