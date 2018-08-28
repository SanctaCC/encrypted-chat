package com.sanctaultras.encryptedchat.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class SimpleWSController {

    @MessageMapping("/test")
    @SendTo("/topic/hello")
    public String hello(@AuthenticationPrincipal User user) throws InterruptedException {
        log.info("{}",user);
        Thread.sleep(2500);
        return "Hello from " + Thread.currentThread().getId();
    }
}
