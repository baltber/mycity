package ru.mycity.core.service.rest.dto;

public class Address {

    private String value;
    private String id;

    public Address(String value, String id) {
        this.value = value;
        this.id = id;
    }

    public Address() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
