package ru.mycity.core.service.dao;

import ru.mycity.core.service.dao.model.DateTimeModel;
import ru.mycity.core.service.dao.model.Order;
import ru.mycity.core.service.dao.model.OrderStat;

import java.util.List;

public interface IStatDao {

    long createOrder(Order order);
    List<OrderStat> getOrderStatList(DateTimeModel dateTimeModel, Integer size, Integer start);

}
