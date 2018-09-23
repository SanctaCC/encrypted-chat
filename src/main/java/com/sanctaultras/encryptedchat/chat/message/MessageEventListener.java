package com.sanctaultras.encryptedchat.chat.message;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageEventListener {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @EventListener
    public void sendMessage(Message.MessageSent sent) {
        simpMessagingTemplate.convertAndSend(sent.getMessage().getParentChatRoom().getId().toString(),
                sent);
    }

}