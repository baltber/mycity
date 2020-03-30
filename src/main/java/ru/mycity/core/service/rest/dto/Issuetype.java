package ru.mycity.core.service.rest.dto;

public class Issuetype {
    private String id;

    public Issuetype(String id) {
        this.id = id;
    }

    public Issuetype() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
