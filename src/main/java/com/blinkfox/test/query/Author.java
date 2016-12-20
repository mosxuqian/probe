package com.blinkfox.test.query;

import java.util.Date;

/**
 * Created by blinkfox on 2016/12/21.
 */
public class Author {

    private Address address;
    private Date birthDate;

    private String name;

    public Author() {
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}