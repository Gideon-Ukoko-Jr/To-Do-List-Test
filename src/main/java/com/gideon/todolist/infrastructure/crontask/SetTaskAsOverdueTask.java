package com.gideon.todolist.infrastructure.crontask;

import com.gideon.todolist.usecase.TaskUseCase;
import lombok.AllArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.inject.Named;

@Named
@AllArgsConstructor
@Component
@EnableScheduling
public class SetTaskAsOverdueTask {

    private final TaskUseCase taskUseCase;

//    @SchedulerLock(name = "SetTaskAsOverdueTask_setTasksAsOverdue")
    @Scheduled(cron = "0 0 0 * * *") // runs by 12:00am midnight every day
//    @Scheduled(cron = "0 0/5 * * * *") //Every fifth minute
    public void setTasksAsOverdue(){
        taskUseCase.setTasksAsOverdue();
    }
}
