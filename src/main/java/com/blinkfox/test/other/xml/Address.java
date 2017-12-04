package com.blinkfox.test.other.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Address地址类.
 *
 * @author blinkfox on 2017-12-04.
 */
public class Address {

    @XmlAttribute
    String country;

    @XmlElement
    String state;

    @XmlElement
    String city;

    @XmlElement
    String street;

    /** 由于没有添加@XmlElement,所以该元素不会出现在输出的xml中. */
    String zipcode;

    /**
     * 默认的空构造方法.
     */
    public Address() {
        super();
    }

    public Address(String country, String state, String city, String street, String zipcode) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    /**
     * country的getter方法.
     *
     * @return country
     */
    public String getCountry() {
        return country;
    }

}