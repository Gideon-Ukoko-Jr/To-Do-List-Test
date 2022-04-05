package com.gideon.todolist.infrastructure.crontask;

import com.gideon.todolist.usecase.TaskUseCase;
import lombok.AllArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;

import javax.inject.Named;

@Named
@AllArgsConstructor
public class SetTaskAsOverdueTask {

    private final TaskUseCase taskUseCase;

    @SchedulerLock(name = "SetTaskAsOverdueTask_setTasksAsOverdue", lockAtMostFor = "PT10M")
    @Scheduled(cron = "0 0 0 ? * * *") // runs by 12:00am midnight every day
    public void setTasksAsOverdue(){
        taskUseCase.setTasksAsOverdue();
    }
}
