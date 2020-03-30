package ru.mycity.core.controller.dto.order;

import ru.mycity.core.controller.dto.ResultDto;

public class OrderResponseDto {
    private ResultDto result;

    public ResultDto getResult() {
        return result;
    }

    public void setResult(ResultDto result) {
        this.result = result;
    }
}
