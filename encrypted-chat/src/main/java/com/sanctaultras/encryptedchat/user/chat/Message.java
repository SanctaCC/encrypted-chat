package com.sanctaultras.encryptedchat.user.chat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sanctaultras.encryptedchat.commons.BaseEntity;
import com.sanctaultras.encryptedchat.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
//    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
//    @JsonIdentityReference(alwaysAsId=true)
    private User author;

}
