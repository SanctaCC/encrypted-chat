package com.sanctaultras.encryptedchat.user;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sanctaultras.encryptedchat.commons.BaseEntity;
import com.sanctaultras.encryptedchat.user.chat.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity<Long> {

    @Column(unique = true)
    private String email;

    private String password;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date date;

    private String encryptedPrivateKey;

    private String publicKey;

    @ManyToMany(mappedBy = "users")
    private Set<ChatRoom> userRooms;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getId());
    }
}
