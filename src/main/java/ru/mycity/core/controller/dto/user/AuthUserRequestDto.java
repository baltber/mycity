package ru.mycity.core.controller.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Запрос авторизации пользователя")
public class AuthUserRequestDto {

    @JsonProperty("login")
    @ApiModelProperty(value = "Имя пользователя")
    private String login;

    @JsonProperty("password")
    @ApiModelProperty(value = "Пароль")
    private String password;

    public AuthUserRequestDto(String login) {
        this.login = login;
    }

    public AuthUserRequestDto() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
