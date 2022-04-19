package com.gideon.todolist.infrastructure.crontask;

import com.gideon.todolist.usecase.TaskUseCase;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Named
@AllArgsConstructor
@Component
@EnableScheduling
public class EmailClientTask {

    TaskUseCase taskUseCase;

    @Scheduled(cron = "0 50 23 * * *")//At 11:50 everyday
    public void emailTaskAlmostDueIn10MToClient() throws MessagingException, UnsupportedEncodingException {
        taskUseCase.sendDueInTenMinutesMail();
    }

    @Scheduled(cron = "0 0 23 * * *")//At 11:00pm everyday
//    @Scheduled(cron = "0 0/5 * * * *")//Test-Every 5 minutes
    public void emailTaskAlmostDueIn1HToClient() throws MessagingException, UnsupportedEncodingException {
        taskUseCase.sendDueInAnHourMail();
    }

    @Scheduled(cron = "0 10 0 * * *")//At 00:10am everyday
    public void emailTaskOverdueBy10MToClient() throws MessagingException, UnsupportedEncodingException {
        taskUseCase.sendOverdueByTenMinutesMail();
    }

    @Scheduled(cron = "0 0 1 * * *")//At 1am everyday
    public void emailTaskOverdueBy1HToClient() throws MessagingException, UnsupportedEncodingException {
        taskUseCase.sendOverdueByAnHourMail();
    }
}
