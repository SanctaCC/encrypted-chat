package com.sanctaultras.encryptedchat.commons;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract class BaseEntity<T> {

    @Id @GeneratedValue
    private T id;
}
