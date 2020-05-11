package ru.mycity.core.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.mycity.core.controller.dto.stat.OrderStatDto;
import ru.mycity.core.service.StatService;

@Component
@RestController
@RequestMapping("/api/stat")
public class StatController {

    @Autowired
    private StatService statService;

    @RequestMapping(path = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "Получить конфигурацию организации по GUID")
    public OrderStatDto getConfig() {
        return statService.getListOrderStat(null, null);
    }
}
