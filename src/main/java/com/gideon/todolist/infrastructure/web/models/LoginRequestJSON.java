package com.gideon.todolist.infrastructure.web.models;

import com.gideon.todolist.usecase.data.requests.LoginRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class LoginRequestJSON {

    @ApiModelProperty(notes = "Login Username", required = true)
    @NotEmpty
    @NotNull
    private String username;

    @ApiModelProperty(notes = "Login Password", required = true)
    @NotEmpty
    @NotNull
    private String password;

    public LoginRequest toRequest(){
        return LoginRequest.builder()
                .username(username)
                .password(password)
                .build();
    }
}
