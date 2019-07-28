package ru.mycity.core.controller.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthUserRequestDto {

    @JsonProperty("user_name")
    private String userName;

    public AuthUserRequestDto(String userName) {
        this.userName = userName;
    }

    public AuthUserRequestDto() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
