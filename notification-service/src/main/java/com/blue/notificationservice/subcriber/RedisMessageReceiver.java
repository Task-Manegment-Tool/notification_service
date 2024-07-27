package com.blue.notificationservice.subcriber;

import com.blue.notificationservice.config.ObjectMap;
import com.blue.notificationservice.enitity.Mail;
import com.blue.notificationservice.enitity.Task;
import com.blue.notificationservice.service.MailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;

@Component
public class RedisMessageReceiver implements MessageListener {




    private MailService mailService;





    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    public RedisMessageReceiver(ObjectMapper objectMapper, MailService mailService) {
        this.objectMapper = objectMapper;
        this.mailService = mailService;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {

        String messageBody = new String(message.getBody(), StandardCharsets.UTF_8);
        System.out.println(messageBody);

        try {
            if (objectMapper == null) {
                throw new RuntimeException("ObjectMapper is null");
            }

            Task task = objectMapper.readValue(messageBody, Task.class);


            mailService.sendNotification(task);


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
}
