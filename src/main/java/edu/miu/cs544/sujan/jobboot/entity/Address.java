package edu.miu.cs544.sujan.jobboot.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String street;
    private String city;
    private String zipCode;
    private String state;

    public Address() {
    }

    public Address(String street, String city, String zipCode, String state) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.state = state;
    }
}