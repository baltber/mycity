package ru.mycity.core.service.dao;

import ru.mycity.core.service.dao.model.DateTimeModel;
import ru.mycity.core.service.dao.model.OrderStat;

import java.sql.Timestamp;
import java.util.List;

public interface IStatDao {

    List<OrderStat> getOrderStatList(DateTimeModel dateTimeModel);

}
