package ru.mycity.core.controller.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.mycity.core.controller.dto.ResultDto;

public class AddUserResponseDto {

    @JsonProperty("result")
    private ResultDto resultDto;

    public ResultDto getResultDto() {
        return resultDto;
    }

    public void setResultDto(ResultDto resultDto) {
        this.resultDto = resultDto;
    }
}
