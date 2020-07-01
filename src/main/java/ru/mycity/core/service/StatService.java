package ru.mycity.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mycity.core.controller.dto.PageableDto;
import ru.mycity.core.controller.dto.order.OrderList;
import ru.mycity.core.controller.dto.order.OrderRequestDto;
import ru.mycity.core.controller.dto.stat.DailyOrderStatDto;
import ru.mycity.core.controller.dto.stat.DishStatDto;
import ru.mycity.core.controller.dto.stat.OrderStatDto;
import ru.mycity.core.service.dao.IStatDao;
import ru.mycity.core.service.dao.model.*;
import ru.mycity.core.utils.Utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatService {

    private IStatDao statDao;

    @Autowired
    public StatService(IStatDao statDao) {
        this.statDao = statDao;
    }

    public long saveOrder(OrderRequestDto requestDto){
        return statDao.createOrder(toStatEntity(requestDto));
    }

    public PageableDto<OrderStatDto> getListOrderStat(String startDate, String endDate, Integer size, Integer start) throws ParseException {

        DateTimeModel dateTimeModel = null;
        dateTimeModel = Utils.getDateTime(startDate, endDate);

        QuerryResult<List<OrderStat >> queryResult = statDao.getOrderStatList(dateTimeModel, size, start);

        OrderStatDto orderStatDto = createOrderStatDto(queryResult.getT());

        return new PageableDto<>(orderStatDto, queryResult.getSize(),
                queryResult.getStart(), queryResult.getTotal());

    }

    public List<DishStatDto> getListDishStat(String startDate, String endDate) throws ParseException {

        DateTimeModel dateTimeModel = null;
        dateTimeModel = Utils.getDateTime(startDate, endDate);

        List<DishStat>queryResult = statDao.getDishStatList(dateTimeModel);

        return createDishStatDto(groupByDish(queryResult));

    }

    public OrderStatDto createOrderStatDto(List<OrderStat> list){
        return new OrderStatDto(
                sumOrder(list),
                sumDelivery(list),
                sumTotal(list),
                avgOrder(list),
                toListDto(list));
    }

    public List<DishStat> groupByDish(List<DishStat> list){
        List<DishStat> res = new ArrayList<>();

        list.stream()
                .collect(Collectors.groupingBy(DishStat::getDishName, Collectors.summingInt(DishStat::getCount)))
                .forEach((id,sumTargetCost)->res.add(new DishStat(id, sumTargetCost)));

        return res;

    }

    public List<DishStatDto> createDishStatDto(List<DishStat> list){
        return list.stream()
                .map(DishStat::toDto)
                .collect(Collectors.toList());
    }

    private List<DailyOrderStatDto> toListDto(List<OrderStat> list){
        return list.stream()
                .map(OrderStat::toDto)
                .collect(Collectors.toList());
    }

    private Order toStatEntity(OrderRequestDto requestDto){
        Order order = new Order();
        order.setClientOrderId(requestDto.getSummary());
        order.setOrderStat(toStatEntity(requestDto.getOrderList()));
        order.setDishStat(toDishStatList(requestDto.getOrderList()));
        return order;
    }

    private OrderStat toStatEntity(OrderList orderList){
        int orderPrice = orderList.getTotalPrice() - orderList.getDeliveryPrice();
        return new OrderStat(orderPrice,
                orderList.getDeliveryPrice(),
                orderList.getTotalPrice());
    }

    private List<DishStat> toDishStatList(OrderList orderList){
        return orderList.getOrderDtoList().stream()
                .map(e-> new DishStat(e.getName(), Integer.valueOf(e.getQuantity())))
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
