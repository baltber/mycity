package ru.mycity.core.service.rest.dto;


public class JiraOrderRequest {
    private Fields fields;

    public JiraOrderRequest(Fields fields) {
        this.fields = fields;
    }

    public JiraOrderRequest() {
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }
}
