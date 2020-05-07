package ru.mycity.core.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.mycity.core.controller.dto.order.FullOrderDto;
import ru.mycity.core.controller.dto.order.OrderRequestDto;
import ru.mycity.core.controller.exception.NotFoundException;
import ru.mycity.core.service.OrderService;
import ru.mycity.core.service.StringOrderService;
import ru.mycity.core.service.rest.dto.JiraSearchResponse;

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
    public String addOrder(@RequestBody OrderRequestDto requestDto) throws NotFoundException {
        return service.add(requestDto);
    }

    @RequestMapping(path = "/add/{guid}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "Добавление нового заказа через Web Hook")
    public String addOrderWithWebHook(@RequestBody String request, @PathVariable String guid) throws NotFoundException {
        log.info("New order: " + request);
        log.info("guid: " + guid);
        if(!"test=test".equals(request)){
            stringOrderService.add(request, guid);
        }

        return "OK";
    }
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Получить список заказов")
    public FullOrderDto addOrder(@RequestParam("guid") String guid,
                                 @RequestParam("state") String state,
                                 @RequestParam(value = "start", required = false) int start,
                                 @RequestParam(value = "size", required = false) int size
                                       ) throws NotFoundException {
        return service.getListOrder(guid, state, start, size);
    }

}
