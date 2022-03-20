package com.gideon.todolist.infrastucture.web.models;

import com.gideon.todolist.usecase.data.requests.RegistrationRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class RegistrationRequestJSON {

    @ApiModelProperty(notes = "Registration Email", required = true)
    @NotEmpty
    @NotNull
    private String email;

    @ApiModelProperty(notes = "Registration Username", required = true)
    @NotEmpty
    @NotNull
    private String username;

    @ApiModelProperty(notes = "Registration Password", required = true)
    @NotEmpty
    @NotNull
    private String password;

    public RegistrationRequest toRequest(){
        return RegistrationRequest.builder()
                .email(email)
                .username(username)
                .password(password)
                .build();
    }
}
