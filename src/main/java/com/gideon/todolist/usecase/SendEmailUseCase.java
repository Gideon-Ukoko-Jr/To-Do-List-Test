package com.gideon.todolist.usecase;

import javax.mail.MessagingException;

public interface SendEmailUseCase {

    void sendAlmostDueEmail() throws MessagingException;
}
