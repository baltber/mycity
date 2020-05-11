package ru.mycity.core.service.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.mycity.core.service.dao.IStatDao;
import ru.mycity.core.service.dao.model.DateTimeModel;
import ru.mycity.core.service.dao.model.OrderStat;
import ru.mycity.core.service.dao.utils.ResourceUtils;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class IStatDaoImpl implements IStatDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public IStatDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<OrderStat> getOrderStatList(DateTimeModel dateTime) {
        String sql = ResourceUtils.resourceAsString(getClass(),"dao/organisation/sql_get_config_by_guid.sql");
        SqlParameterSource params = new MapSqlParameterSource("start_date", dateTime.getStartDate())
                .addValue("end_date", dateTime.getEndDate());

        return jdbcTemplate.query(sql, params, createRowMapper());
    }

    private RowMapper<OrderStat> createRowMapper(){
        return ((rs, rowNum) -> new OrderStat());
    }
}

