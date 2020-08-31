package ru.mycity.core.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.mycity.core.controller.dto.PageableDto;
import ru.mycity.core.controller.dto.stat.DishStatDto;
import ru.mycity.core.controller.dto.stat.OrderStatDto;
import ru.mycity.core.controller.exception.BadRequestException;
import ru.mycity.core.service.StatService;

import java.text.ParseException;
import java.util.List;

@Component
@RestController
@RequestMapping("/api/stat")
public class StatController {

    @Autowired
    private StatService statService;

    @RequestMapping(path = "/price", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "Получить статистику сумм заказов")
    public PageableDto<OrderStatDto> getPriceStat(@RequestParam(value = "startDate", required = false) String startDate,
                                                  @RequestParam(value = "endDate", required = false) String endDate,
                                                  @RequestParam(value = "start", required = false) Integer start,
                                                  @RequestParam(value = "size", required = false) Integer size,
                                                  @RequestParam(value = "groupType", required = false) String groupType
                                     ) throws BadRequestException {
        try {
            int defaultSize=10;
            int defaultStart=0;
            if(size==null){
                size=defaultSize;
            }
            if(start==null){
                start=defaultStart;
            }
            return statService.getListOrderStat(startDate, endDate, size, start, groupType);
        } catch (ParseException e) {
            throw new BadRequestException("Неверный формат даты");
        }
    }

    @RequestMapping(path = "/dish", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "Получить статистику блюд")
    public List<DishStatDto> getDishStat(@RequestParam(value = "startDate", required = false) String startDate,
                                                      @RequestParam(value = "endDate", required = false) String endDate
    ) throws BadRequestException {
        try {

            return statService.getListDishStat(startDate, endDate);
        } catch (ParseException e) {
            throw new BadRequestException("Неверный формат даты");
        }
    }
}
