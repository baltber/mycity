package ru.mycity.core.service.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.mycity.core.service.dao.IStatDao;
import ru.mycity.core.service.dao.Querry;
import ru.mycity.core.service.dao.model.*;
import ru.mycity.core.service.dao.utils.ResourceUtils;
import ru.mycity.core.utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class StatDaoImpl implements IStatDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public StatDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long createOrder(Order order) {
        long orderId = createOrderInner(order);
        createDishStatList(order.getDishStat(), orderId);
        return orderId;
    }


    public long createOrderInner(Order order) {
        KeyHolder kh = new GeneratedKeyHolder();

        String sql = ResourceUtils.resourceAsString(getClass(),"dao/stat/sql_insert_order.sql");
        long orderStatId=createOrderStat(order.getOrderStat());

        SqlParameterSource params = new MapSqlParameterSource("order_stat_id", orderStatId)
                .addValue("order_date", Utils.createTimestampNow());

        jdbcTemplate.update(sql, params, kh, new String[]{"order_id"});

        return kh.getKey().longValue();
    }

    public long createOrderStat(OrderStat orderStat){
        KeyHolder kh = new GeneratedKeyHolder();

        String sql = ResourceUtils.resourceAsString(getClass(),"dao/stat/sql_insert_order_stat.sql");

        SqlParameterSource params = new MapSqlParameterSource("order_price", orderStat.getOrderPrice())
                .addValue("delivery_price", orderStat.getDeliveryPrice())
                .addValue("total_price", orderStat.getTotalPrice());

        jdbcTemplate.update(sql, params, kh, new String[]{"order_stat_id"});

        return kh.getKey().longValue();
    }

    public void createDishStatList(List<DishStat> list, long orderId){
        list.forEach(e->createDishStat(e, orderId));
    }

    public void createDishStat(DishStat dishStat, long orderId){

        String sql = ResourceUtils.resourceAsString(getClass(),"dao/stat/sql_insert_dish_stat.sql");

        SqlParameterSource params = new MapSqlParameterSource("dish_name", dishStat.getDishName())
                .addValue("count", Integer.valueOf(dishStat.getCount()))
                .addValue("order_id", orderId);

        jdbcTemplate.update(sql, params);

    }

    @Override
    public  QuerryResult<List<OrderStat>> getOrderStatList(DateTimeModel dateTime, Integer size, Integer start) {

        StringJoiner where = new Querry().init()
                .withDates(dateTime)
                .withPageable(size, start)
                .build();

        String sql = ResourceUtils.resourceAsString(getClass(),"dao/stat/sql_get_order_stat.sql")
                + where;
        SqlParameterSource params = new MapSqlParameterSource("start_date", dateTime.getStartDate())
                .addValue("end_date", dateTime.getEndDate())
                .addValue("limit", size)
                .addValue("offset", start);
        List<OrderStat> list = jdbcTemplate.query(sql, params, createRowMapper());

        String sqlTotal = ResourceUtils.resourceAsString(getClass(),"dao/stat/sql_get_order_stat_total.sql");

        Long total = jdbcTemplate.queryForObject(sqlTotal, new HashMap<>(), Long.class);


        return new QuerryResult<>(list, size, start, total);
    }

    private RowMapper<OrderStat> createRowMapper(){
        return ((rs, rowNum) -> new OrderStat(rs.getInt("order_price"),
                rs.getInt("delivery_price"),
                rs.getInt("total_price")));
    }
}

