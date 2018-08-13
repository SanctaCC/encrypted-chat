package com.sanctaultras.encryptedchat.user.chat;

import com.sanctaultras.encryptedchat.commons.BaseEntity;
import com.sanctaultras.encryptedchat.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Message extends BaseEntity {

    private String body;

    private Date dateSent;

    @ManyToOne
    private ChatRoom parentChatRoom;

    @ManyToOne
    private User author;

}
