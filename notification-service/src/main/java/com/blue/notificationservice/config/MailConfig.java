//package com.blue.notificationservice.config;
//
//import jakarta.mail.internet.MimeMessage;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.MailException;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessagePreparator;
//
//import java.io.InputStream;
//
//@Configuration
//public class MailConfig {
//
//    @Bean
//    public JavaMailSender mailSender(){
//        return new JavaMailSender() {
//            @Override
//            public void send(SimpleMailMessage simpleMessage) throws MailException {
//                JavaMailSender.super.send(simpleMessage);
//            }
//
//            @Override
//            public void send(SimpleMailMessage... simpleMessages) throws MailException {
//
//            }
//
//            @Override
//            public MimeMessage createMimeMessage() {
//                return null;
//            }
//
//            @Override
//            public MimeMessage createMimeMessage(InputStream contentStream) throws MailException {
//                return null;
//            }
//
//            @Override
//            public void send(MimeMessage mimeMessage) throws MailException {
//                JavaMailSender.super.send(mimeMessage);
//            }
//
//            @Override
//            public void send(MimeMessage... mimeMessages) throws MailException {
//
//            }
//
//            @Override
//            public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {
//                JavaMailSender.super.send(mimeMessagePreparator);
//            }
//
//            @Override
//            public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException {
//                JavaMailSender.super.send(mimeMessagePreparators);
//            }
//        };
//    }
//}
