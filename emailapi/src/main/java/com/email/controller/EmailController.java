package com.email.controller;

import com.email.model.EmailRequest;
import com.email.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;
    

    //hello handler
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }


    //api to send mail
    
    //data sent by user comes here in form of json.
    @PostMapping("/sendemail")
   public ResponseEntity<?> sendEmail (@RequestBody EmailRequest request){

    System.out.println(request);
    boolean result = this.emailService.sendEmail(request.getSubject(), request.getMessage(), request.getTo()); //we have pass 3 things, create a model to recieve

    if(result)
        return ResponseEntity.ok("Mail Sent Successfully");
    else
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Oops! Something Went Wrong...Mail not sent...!!!");
   }
}