package com.sanctaultras.encryptedchat.commons;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity {

    @Id @GeneratedValue
    private Long id;
}
