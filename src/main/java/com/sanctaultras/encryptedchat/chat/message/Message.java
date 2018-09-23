package com.sanctaultras.encryptedchat.chat.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sanctaultras.encryptedchat.chat.room.ChatRoom;
import com.sanctaultras.encryptedchat.commons.BaseEntity;
import com.sanctaultras.encryptedchat.commons.BaseEntitySerializer;
import com.sanctaultras.encryptedchat.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Message extends BaseEntity<Long> {

    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private ChatRoom parentChatRoom;

    @ManyToOne
    @JsonSerialize(using = BaseEntitySerializer.class)
    private User author;

    Message send() {
        registerEvent(MessageSent.of(this));
        return this;
    }

    @Value(staticConstructor = "of")
    public static class MessageSent {
        private Message message;
    }

}