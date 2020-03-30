package ru.mycity.core.service.rest.dto;

import java.util.List;

public class Content {
    private String type;
    private List<OrderContent> content;

    public Content(String type, List<OrderContent> content) {
        this.type = type;
        this.content = content;
    }

    public Content() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<OrderContent> getContent() {
        return content;
    }

    public void setContent(List<OrderContent> content) {
        this.content = content;
    }
}
