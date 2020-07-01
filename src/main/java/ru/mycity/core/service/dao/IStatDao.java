package ru.mycity.core.service.dao;

import ru.mycity.core.service.dao.model.*;

import java.util.List;

public interface IStatDao {

    long createOrder(Order order);
    QuerryResult<List<OrderStat>> getOrderStatList(DateTimeModel dateTimeModel, Integer size, Integer start);
    List<DishStat> getDishStatList(DateTimeModel dateTimeModel);

}
