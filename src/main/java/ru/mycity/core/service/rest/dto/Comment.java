package ru.mycity.core.service.rest.dto;

import java.util.List;

public class Comment {
    private int version;
    private String type;
    private List<Content> content;

    public Comment(int version, String type, List<Content> content) {
        this.version = version;
        this.type = type;
        this.content = content;
    }

    public Comment() {
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }
}