package com.gideon.todolist.usecase.impl;

import com.gideon.todolist.domain.dao.TaskEntityDao;
import com.gideon.todolist.domain.entities.TaskEntity;
import com.gideon.todolist.service.EmailSenderService;
import com.gideon.todolist.usecase.SendEmailUseCase;
import com.gideon.todolist.usecase.models.MailModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;
import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@AllArgsConstructor
@Slf4j
public class SendEmailUseCaseImpl implements SendEmailUseCase {

    private final TaskEntityDao taskEntityDao;
    private final EmailSenderService emailSenderService;

    @Override
    public void sendAlmostDueEmail() throws MessagingException {
        List<TaskEntity> tasks = taskEntityDao.getOverdueTasks();
        if (tasks.isEmpty()){
            return;
        }
        for (TaskEntity task : tasks){
            if (task.getDueDate().getMonth() == LocalDate.now().getMonth()){
                if (task.getDueDate().getDayOfMonth() == LocalDate.now().getDayOfMonth()+1){
                    MailModel mail = new MailModel();
                    mail.setFrom("gideon.ukokojr@gmail.com");
                    mail.setMailTo(task.getUser().getEmail());
                    mail.setSubject("Your Task is Almost Due");

                    Map<String, Object> model = new HashMap<String, Object>();
                    model.put("receiverName", task.getUser().getEmail());
                    model.put("taskName", task.getTaskName());
                    model.put("dueDate", task.getDueDate().toString());
                    mail.setProps(model);
                    emailSenderService.sendAlmostDueEmail(mail);
                }
            }
        }
    }
}
