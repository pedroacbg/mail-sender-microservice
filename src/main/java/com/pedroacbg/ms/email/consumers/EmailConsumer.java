package com.pedroacbg.ms.email.consumers;

import com.pedroacbg.ms.email.dtos.EmailDTO;
import com.pedroacbg.ms.email.models.Email;
import com.pedroacbg.ms.email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    private EmailService service;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDTO dto){
        Email email = new Email();
        BeanUtils.copyProperties(dto, email);
        service.sendEmail(email);
    }

}
