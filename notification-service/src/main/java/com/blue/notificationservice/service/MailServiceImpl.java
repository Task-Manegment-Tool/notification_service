package com.blue.notificationservice.service;

import com.blue.notificationservice.enitity.Mail;
import com.blue.notificationservice.enitity.Task;
import com.blue.notificationservice.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TaskService taskService;

    @Override
    public Mail sendNotification(Task task){
        Mail mail= new Mail();



       if(Long.toString(task.getId()).startsWith("100") ){

        mail=   createTaskMail(task);
        mail.setUserId(task.getUserId());
       }
       else if (Long.toString(task.getId()).startsWith("101")) {
            mail = updateTaskMail(task);
           mail.setUserId(task.getUserId());


       }
       else if (Long.toString(task.getId()).startsWith("102")){
            mail = expiredTaskMail(task);
           mail.setUserId(task.getUserId());

       }else {

           mail.setTo("Error@gamil.com");
           mail.setSubject("mail didn't send ");
           mail.setText("ERRORRRRRRRRRRRRRRRR   \r\n" +
                   "mail is not send bcoz of some error");

       }




        try {
            SimpleMailMessage messasge = new SimpleMailMessage();
            messasge.setTo(mail.getTo());
            messasge.setSubject(mail.getSubject());
            messasge.setText(mail.getText());
            System.out.println(messasge);

            mailSender.send(messasge);
        }
        catch(MailException m ){
            System.out.println("mail error");
            throw m;
        }
return mail;

    }




    @Override
    public List<Mail> getNotificationByUserId(long userId) {
//        ResponseEntity<List<Task>> taskList =     taskService.getTasksByUserId(userId);
//        ResponseEntity<List<Task>> response = taskList;
//        System.out.println(response);

        List<Mail> listOfNotificationByUserId = notificationRepository.findByuserId(userId);

        return  listOfNotificationByUserId;
    }

//mail format for crete task
public Mail createTaskMail (Task task){
    Mail mail = new Mail();
    mail.setTo("palaskara749@gmail.com");
    mail.setSubject("New Task Assinged");
    mail.setText("Hi there, "+task.getUserId()+"!\r \n" +
            " \r \n" +
            "A New task is assinged tp you , this mail is to let u know your task \r\n " +
            "Task Name : "+task.getName()+" is assinged . \r\n " +
            "Task Status : " +task.getStatus()+"\r\n"+
            "Task Description : "+task.getDescription()+" and its expired time is "+task.getExpiryTime() );
    return  mail;
}


//mail formate for update task
    public Mail updateTaskMail (Task task){
        Mail mail = new Mail();
        mail.setTo("palaskara749@gmail.com");
        mail.setSubject("Task Updated");
        mail.setText("Hi there, "+task.getUserId()+"!\r \n" +
                " \r \n" +
                "The task you have is updated. This email informs you of your revised job.\r\n " +
                "Task Name : "+task.getName()+" is updated. \r\n " +
                "Task Status : " +task.getStatus()+"\r\n"+
                "Task Description : "+task.getDescription()+" and its expired time is "+task.getExpiryTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))    );
        return  mail;
    }


//mail formate for wxpired task
    public Mail expiredTaskMail (Task task){
        Mail mail = new Mail();
        mail.setTo("palaskara749@gmail.com");
        mail.setSubject("Reminder Task Expired");
        mail.setText("Hi there, "+task.getUserId()+"!\r \n" +
                " \r \n" +
                "You have not finished your task. Please finish the work.Which is assinged to you \r\n " +
                "Task Name : "+task.getName()+" is assinged . \r\n " +
                "Task Status : " +task.getStatus()+"\r\n"+
                "Task Description : "+task.getDescription()+" and its expired time is "+task.getExpiryTime() );
        return  mail;
    }



}
