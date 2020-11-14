package com.geekbrains.springshop.services;

import com.geekbrains.springshop.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class MailService {

    private JavaMailSender javaMailSender;
    private MailMessageBuilder messageBuilder;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Autowired
    public void setMessageBuilder(MailMessageBuilder messageBuilder) {
        this.messageBuilder = messageBuilder;
    }

    public void sendMail(String email, String subject, String text) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

        try {
            helper.setTo(email);
            helper.setText(text, true);
            helper.setSubject(subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        try {
            executorService.submit(() -> javaMailSender.send(message));
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

    public void sendOrderMail(Order order) {
        sendMail(order.getUser().getEmail(), String.format("Заказ %d%n отправлен в обработку", order.getId()), messageBuilder.buildOrderEmail(order));
    }
}