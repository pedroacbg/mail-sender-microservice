package com.pedroacbg.ms.email.services;

import com.pedroacbg.ms.email.enums.StatusEmail;
import com.pedroacbg.ms.email.models.Email;
import com.pedroacbg.ms.email.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    private EmailRepository repository;

    @Autowired
    private JavaMailSender emailSender;

    public Email sendEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            emailSender.send(message);
            email.setStatus(StatusEmail.SENT);
        }catch(MailException e){
            email.setStatus(StatusEmail.ERROR);
        }finally {
            return repository.save(email);
        }
    }
}
