package ru.mycity.core.service.rest.dto;

public class OrderContent {
    private String type;
    private String text;

    public OrderContent(String type, String text) {
        this.type = type;
        this.text = text;
    }

    public OrderContent() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
