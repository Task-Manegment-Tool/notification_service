package com.blue.notificationservice.controller;

import com.blue.notificationservice.enitity.Mail;
import com.blue.notificationservice.enitity.Task;
import com.blue.notificationservice.service.MailService;
import com.blue.notificationservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/notify")
public class NotificationController {

    @Autowired
    private MailService mailService;



    Logger logger = Logger.getLogger(NotificationController.class.getName());

     @PostMapping("/send")
     public void sendNotification(@RequestBody Task task){
      mailService.sendNotification(task);

      logger.info("Send Success");
    }

    @GetMapping("/sendNotiByUser/{userId}")
    public ResponseEntity<List<Mail>> getNotificationbyUserId(@PathVariable long userId){
        logger.info("getNotification by user id  started ");

      List<Mail> listNotification = mailService.getNotificationByUserId(userId);

        logger.info("Send Success");
        return ResponseEntity.ok(listNotification);
    }

}
