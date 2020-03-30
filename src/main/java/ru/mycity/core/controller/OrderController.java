package ru.mycity.core.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.mycity.core.controller.dto.order.OrderRequestDto;
import ru.mycity.core.controller.dto.order.OrderResponseDto;
import ru.mycity.core.service.OrderService;

@Component
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService service;


    @RequestMapping(path = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "Добавление нового заказа")
    public String auth(@RequestBody OrderRequestDto requestDto) {
        return service.add(requestDto);
    }

}
