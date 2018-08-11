package com.sanctaultras.encryptedchat.user;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sanctaultras.encryptedchat.commons.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity{

    private String email;

    private String password;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date date;

    private String encryptedPrivateKey;

    private String publicKey;

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
