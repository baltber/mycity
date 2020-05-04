package ru.mycity.core.service.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Issue {

    @JsonProperty("key")
    private String key;
    @JsonProperty("fields")
    private Fields fields;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }
}
