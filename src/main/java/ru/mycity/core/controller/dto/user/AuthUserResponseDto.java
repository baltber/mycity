package ru.mycity.core.controller.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.mycity.core.controller.dto.ResultDto;

public class AuthUserResponseDto {
    @JsonProperty("result")
    private ResultDto resultDto;
    @JsonProperty("user")
    private UserDto userDto;

    public ResultDto getResultDto() {
        return resultDto;
    }

    public void setResultDto(ResultDto resultDto) {
        this.resultDto = resultDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
