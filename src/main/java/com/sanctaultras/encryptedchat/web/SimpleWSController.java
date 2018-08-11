package com.sanctaultras.encryptedchat.web;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SimpleWSController {

    @MessageMapping("/test")
    @SendTo("/topic/hello")
    public String hello() throws InterruptedException {
        Thread.sleep(2000);
        return "Hello from " + Thread.currentThread().getId();
    }
}
