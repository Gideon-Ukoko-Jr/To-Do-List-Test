package com.gideon.todolist.infrastructure.crontask;

import com.gideon.todolist.usecase.SendEmailUseCase;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import javax.mail.MessagingException;

@Named
@AllArgsConstructor
@Component
@EnableScheduling
public class EmailClientTask {

    private final SendEmailUseCase sendEmailUseCase;

    @Scheduled(cron = "0 0/5 * * * *")
    public void emailTaskAlmostDueToClient() throws MessagingException {
        sendEmailUseCase.sendAlmostDueEmail();
    }//Every fifth minute
}
