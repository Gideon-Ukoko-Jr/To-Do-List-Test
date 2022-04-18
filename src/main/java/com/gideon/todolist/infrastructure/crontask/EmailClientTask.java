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

    @Scheduled(cron = "0 0/5 * * * *")//Every fifth minute
    public void emailTaskAlmostDueToClient() throws MessagingException, UnsupportedEncodingException {
        taskUseCase.sendAlmostDueEmail();
    }
}
