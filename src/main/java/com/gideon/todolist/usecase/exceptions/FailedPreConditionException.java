package com.gideon.todolist.usecase.exceptions;

public class FailedPreConditionException extends RuntimeException  {
    public FailedPreConditionException(String message){
        super(message);
    }
}
