package com.gideon.todolist.usecase.exceptions;

public class BusinessLogicConflictException extends RuntimeException{
    public BusinessLogicConflictException(String message){
        super(message);
    }
}
