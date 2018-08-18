package com.sanctaultras.encryptedchat.user.chat;

import com.sanctaultras.encryptedchat.commons.BaseEntity;
import com.sanctaultras.encryptedchat.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom extends BaseEntity<UUID> {

    private String name;

    @ManyToMany
    @JoinTable(name = "chat_user",
            joinColumns = @JoinColumn(name = "chatroom_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id")
    )
//    @JsonSerialize(using=UserSetSerializer.class)
    private Set<User> users;

}
//class UserSetSerializer extends JsonSerializer<Set<User>> {

//    @Override
//    public void serialize(Set<User> value, JsonGenerator jsonGenerator, SerializerProvider provider)
//            throws IOException {
//
//        jsonGenerator.writeStartArray();
//        value.forEach( p-> {
//            try {
//                jsonGenerator.writeStartObject();
//                jsonGenerator.writeNumberField("id",p.getId());
//                jsonGenerator.writeStringField("email",p.getEmail());
//                jsonGenerator.writeEndObject();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//
//        });
//        jsonGenerator.writeEndArray();
//    }
//}
