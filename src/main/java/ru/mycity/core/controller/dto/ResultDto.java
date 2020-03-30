package ru.mycity.core.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultDto {
    @JsonProperty(value = "status_code")
    private String statusCode;
    private String message;

    public ResultDto() {
    }

    public ResultDto(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}