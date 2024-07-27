package com.blue.notificationservice.service;

import com.blue.notificationservice.enitity.Mail;
import com.blue.notificationservice.enitity.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MailService {

    public Mail sendNotification(Task task);
    public List<Mail> getNotificationByUserId(long userId);
}
