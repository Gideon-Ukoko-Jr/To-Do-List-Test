package com.gideon.todolist.usecase.data.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponse {

    private String authenticationToken;
    private String username;

}
