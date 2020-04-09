package ru.mycity.core.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.mycity.core.controller.dto.order.OrderRequestDto;
import ru.mycity.core.service.OrderService;
import ru.mycity.core.service.StringOrderService;

@Component
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService service;
    @Autowired
    private StringOrderService stringOrderService;
    private Logger log = LoggerFactory.getLogger(OrderController.class);


    @RequestMapping(path = "/new", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "Добавление нового заказа")
    public String addOrder(@RequestBody OrderRequestDto requestDto) {
        return service.add(requestDto);
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "Добавление нового заказа через Web Hook")
    public String addOrderWithWebHook(@RequestBody String request) {
        log.info("New order: " + request);
        if(!"test=test".equals(request)){
            stringOrderService.add(request);
        }

        return "OK";
    }

}
