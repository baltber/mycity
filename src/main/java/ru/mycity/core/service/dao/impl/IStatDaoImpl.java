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
import ru.mycity.core.service.dao.model.DateTimeModel;
import ru.mycity.core.service.dao.model.DishStat;
import ru.mycity.core.service.dao.model.Order;
import ru.mycity.core.service.dao.model.OrderStat;
import ru.mycity.core.service.dao.utils.ResourceUtils;
import ru.mycity.core.utils.Utils;

import java.util.List;
import java.util.StringJoiner;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class IStatDaoImpl implements IStatDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public IStatDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public long createOrder(Order order) {
        KeyHolder kh = new GeneratedKeyHolder();

        String sql = ResourceUtils.resourceAsString(getClass(),"dao/organisation/sql_get_config_by_guid.sql");
        long orderStatId=createOrderStat(order.getOrderStat());
        long dishStatId=createDishStat(order.getDishStat());


        SqlParameterSource params = new MapSqlParameterSource("order_stat_id", orderStatId)
                .addValue("dish_stat_id", dishStatId)
                .addValue("order_date", Utils.createTimestampNow())
                .addValue("inner_id", order.getInnerId())
                .addValue("client_order_id", order.getClientOrderId());

        jdbcTemplate.update(sql, params, kh, new String[]{"order_id"});

        return kh.getKey().longValue();
    }

    public long createOrderStat(OrderStat orderStat){
        KeyHolder kh = new GeneratedKeyHolder();

        String sql = ResourceUtils.resourceAsString(getClass(),"dao/organisation/sql_get_config_by_guid.sql");

        SqlParameterSource params = new MapSqlParameterSource("order_price", orderStat.getOrderPrice())
                .addValue("delivery_price", orderStat.getDeliveryPrice())
                .addValue("total_price", orderStat.getTotalPrice());

        jdbcTemplate.update(sql, params, kh, new String[]{"order_stat_id"});

        return kh.getKey().longValue();
    }

    public long createDishStat(DishStat dishStat){
        KeyHolder kh = new GeneratedKeyHolder();

        String sql = ResourceUtils.resourceAsString(getClass(),"dao/organisation/sql_get_config_by_guid.sql");

        SqlParameterSource params = new MapSqlParameterSource("dish_name", dishStat.getDishName())
                .addValue("count", dishStat.getCount());

        jdbcTemplate.update(sql, params, kh, new String[]{"dish_stat_id"});

        return kh.getKey().longValue();
    }

    @Override
    public List<OrderStat> getOrderStatList(DateTimeModel dateTime) {
        StringJoiner where = (new StringJoiner("", " WHERE ", "")).setEmptyValue("");
        where.add(" 1 = 1");

        if (dateTime.getStartDate() != null && dateTime.getEndDate() != null) {
            where.add(" AND order_date BETWEEN :start_date AND :end_date");
        } else if(dateTime.getStartDate() != null){
            where.add(" AND order_date >= :start_date");
        }

        String sql = ResourceUtils.resourceAsString(getClass(),"dao/stat/sql_get_order_stat.sql")
                + where;
        SqlParameterSource params = new MapSqlParameterSource("start_date", dateTime.getStartDate())
                .addValue("end_date", dateTime.getEndDate());

        return jdbcTemplate.query(sql, params, createRowMapper());
    }

    private RowMapper<OrderStat> createRowMapper(){
        return ((rs, rowNum) -> new OrderStat(rs.getInt("order_price"),
                rs.getInt("delivery_price"),
                rs.getInt("total_price")));
    }
}

