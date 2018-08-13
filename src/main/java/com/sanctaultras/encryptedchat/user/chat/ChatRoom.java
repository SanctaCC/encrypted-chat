package com.sanctaultras.encryptedchat.user.chat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sanctaultras.encryptedchat.commons.BaseEntity;
import com.sanctaultras.encryptedchat.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom extends BaseEntity<UUID> {

    private String name;

    @ManyToMany(mappedBy = "userRooms")
    @JsonSerialize(using=UserSetSerializer.class)
    private Set<User> users;

}
class UserSetSerializer extends JsonSerializer<Set<User>> {

    @Override
    public void serialize(Set<User> value, JsonGenerator jsonGenerator, SerializerProvider provider)
            throws IOException {

        jsonGenerator.writeStartArray();
        value.forEach( p-> {
            try {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("id",p.getId());
                jsonGenerator.writeStringField("email",p.getEmail());
                jsonGenerator.writeEndObject();
            } catch (IOException e) {
                e.printStackTrace();
            }



        });
        jsonGenerator.writeEndArray();
    }
}
