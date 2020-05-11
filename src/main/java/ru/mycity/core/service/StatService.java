package ru.mycity.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mycity.core.controller.dto.order.OrderList;
import ru.mycity.core.controller.dto.order.OrderRequestDto;
import ru.mycity.core.controller.dto.stat.DailyOrderStatDto;
import ru.mycity.core.controller.dto.stat.OrderStatDto;
import ru.mycity.core.service.dao.IStatDao;
import ru.mycity.core.service.dao.model.DateTimeModel;
import ru.mycity.core.service.dao.model.DishStat;
import ru.mycity.core.service.dao.model.Order;
import ru.mycity.core.service.dao.model.OrderStat;
import ru.mycity.core.utils.Utils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatService {

    private IStatDao statDao;

    public long saveOrder(OrderRequestDto requestDto){
        return statDao.createOrder(toStatEntity(requestDto));
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
                .map(e-> new DishStat(e.getName(), String.valueOf(e.getQuantity())))
                .collect(Collectors.toList());
    }

    @Autowired
    public StatService(IStatDao statDao) {
        this.statDao = statDao;
    }

    public OrderStatDto getListOrderStat(String startDate, String endDate){

        DateTimeModel dateTimeModel = Utils.getDateTime(startDate, endDate);

        return createOrderStatDto(statDao.getOrderStatList(dateTimeModel));
    }

    public OrderStatDto createOrderStatDto(List<OrderStat> list){
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
