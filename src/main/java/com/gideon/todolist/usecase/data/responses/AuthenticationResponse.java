package com.gideon.todolist.usecase.data.responses;

import com.gideon.todolist.usecase.models.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponse {

    long id;
    String username;
    String email;
    String type;
    String token;

}
