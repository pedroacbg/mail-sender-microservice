package com.pedroacbg.ms.email.controllers;

import com.pedroacbg.ms.email.dtos.EmailDTO;
import com.pedroacbg.ms.email.models.Email;
import com.pedroacbg.ms.email.services.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class EmailController {

    @Autowired
    private EmailService service;

    @PostMapping("/sending-email")
    public ResponseEntity<Email> sendingEmail(@RequestBody @Valid EmailDTO dto){
        Email email = new Email();
        BeanUtils.copyProperties(dto, email);
        service.sendEmail(email);
        return new ResponseEntity<>(email, HttpStatus.CREATED);
    }

}
