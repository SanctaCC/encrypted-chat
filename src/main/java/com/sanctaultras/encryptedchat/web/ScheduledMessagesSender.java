package com.sanctaultras.encryptedchat.web;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledMessagesSender {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public ScheduledMessagesSender(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Scheduled(fixedDelay = 10000)
    public void pushMessages() {
        simpMessagingTemplate.convertAndSend("/topic/hello","message");
    }
}
