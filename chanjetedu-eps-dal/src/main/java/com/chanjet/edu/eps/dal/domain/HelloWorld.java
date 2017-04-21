package com.chanjet.edu.eps.dal.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@ToString(callSuper = true)
@Table(name = "HELLO_WORLD")
@EqualsAndHashCode
public class HelloWorld extends IdentityTimestamp {
    @Column(name = "KEY")
    private String key;

    @Column(name = "VALUE")
    private String value;

}