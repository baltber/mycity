package ru.mycity.core.controller.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetUserRequestDto {

    @JsonProperty("user")
    private UserDto userDto;

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
