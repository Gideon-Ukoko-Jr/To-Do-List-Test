package com.gideon.todolist.usecase.exceptions;

public class UnauthorisedException extends RuntimeException {
    public UnauthorisedException(String message){
        super(message);
    }
}
