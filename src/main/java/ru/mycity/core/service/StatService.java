package ru.mycity.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mycity.core.controller.dto.stat.DailyOrderStatDto;
import ru.mycity.core.controller.dto.stat.OrderStatDto;
import ru.mycity.core.service.dao.IStatDao;
import ru.mycity.core.service.dao.model.DateTimeModel;
import ru.mycity.core.service.dao.model.OrderStat;
import ru.mycity.core.utils.Utils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatService {

    private IStatDao statDao;

    @Autowired
    public StatService(IStatDao statDao) {
        this.statDao = statDao;
    }

    public OrderStatDto getListOrderStat(String startDate, String endDate){

        DateTimeModel dateTimeModel = Utils.getDateTime(startDate, endDate);

        return createOrderStat(statDao.getOrderStatList(dateTimeModel));
    }

    public OrderStatDto createOrderStat(List<OrderStat> list){
        return new OrderStatDto(
                sumOrder(list),
                sumDelivery(list),
                sumTotal(list),
                avgOrder(list),
                toListDto(list));
    }

    private List<DailyOrderStatDto> toListDto(List<OrderStat> list){
        return list.stream()
                .map(OrderStat::toDto)
                .collect(Collectors.toList());
    }


    private Integer sumOrder(List<OrderStat> list){
        return list.stream()
                .map(OrderStat::getOrderPrice)
                .reduce(0, Integer::sum);
    }

    private Integer sumDelivery(List<OrderStat> list){
        return list.stream()
                .map(OrderStat::getDeliveryPrice)
                .reduce(0, Integer::sum);
    }

    private Integer sumTotal(List<OrderStat> list){
        return list.stream()
                .map(OrderStat::getTotalPrice)
                .reduce(0, Integer::sum);
    }

    private Double avgOrder(List<OrderStat> list){
        return list.stream()
                .mapToInt(OrderStat::getTotalPrice)
                .average()
                .orElse(Double.NaN);
    }
}
