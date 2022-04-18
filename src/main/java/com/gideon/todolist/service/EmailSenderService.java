package com.gideon.todolist.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.inject.Named;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Service
@Data
@AllArgsConstructor
@Named
@Slf4j
public class EmailSenderService {

    private static final String TEMPLATE_NAME = "almost-due-email-template";

    private final Environment environment;
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public void sendMail(String name, String sendToEmail, String subject, String task) throws MessagingException, UnsupportedEncodingException {

        Properties properties = new Properties();
//        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.ssl.trust", "*");

        String mailFrom = environment.getProperty("spring.mail.properties.mail.smtp.from");
        String mailFromName = environment.getProperty("mail.from.name", "Identity");

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper email;
        email = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        email.setTo(sendToEmail);
        email.setSubject(subject);
        email.setFrom(new InternetAddress(mailFrom, mailFromName));

        final Context ctx = new Context(LocaleContextHolder.getLocale());

        ctx.setVariable("email", sendToEmail);
        ctx.setVariable("name", name);
        ctx.setVariable("taskName", task);

        final String htmlContent = this.templateEngine.process(TEMPLATE_NAME, ctx);

        email.setText(htmlContent, true);

        mailSender.send(mimeMessage);

        log.info("Email Sent Successfully");
    }
}
