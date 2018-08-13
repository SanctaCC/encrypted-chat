package com.sanctaultras.encryptedchat.user.chat;

import com.sanctaultras.encryptedchat.commons.BaseEntity;
import com.sanctaultras.encryptedchat.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom extends BaseEntity {

    private String name;

    @ManyToMany(mappedBy = "userRooms")
    private Set<User> users;

}
