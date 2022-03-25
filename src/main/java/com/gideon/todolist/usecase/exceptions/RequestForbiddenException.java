package com.gideon.todolist.usecase.exceptions;

public class RequestForbiddenException extends RuntimeException {
    public RequestForbiddenException(String message){
        super(message);
    }
}
