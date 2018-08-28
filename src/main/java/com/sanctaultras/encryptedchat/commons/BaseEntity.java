package com.sanctaultras.encryptedchat.commons;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Date;

@Getter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract class BaseEntity<T> implements Persistable<T>{

    @Id @GeneratedValue
    private T id;

    private Date createdDate;

    private Date lastModified;

    @PrePersist
    private void prePersist() {
        createdDate = new Date();
    }

    @PreUpdate
    private void preUpdate() {
        lastModified = new Date();
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return id==null;
    }
}
