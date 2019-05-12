package ru.mycity.core.service.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.mycity.core.service.dao.IComplaintDao;
import ru.mycity.core.service.dao.model.Complaint;
import ru.mycity.core.service.dao.utils.ResourceUtils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;


@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ComplaintDaoImpl implements IComplaintDao {

    private final JdbcOperations jdbcOperations;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ComplaintDaoImpl(JdbcOperations jdbcOperations, NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcOperations = jdbcOperations;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Complaint> getComplaints(String category, Timestamp creationTimeStart, Timestamp creationTimeEnd){
        StringJoiner where = new StringJoiner("", " WHERE ", "").setEmptyValue("");

        if (category != null) {
            where.add(" AND category = :category");
        }
        if (creationTimeStart != null && creationTimeEnd != null) {
            where.add(" AND (creation_time BETWEEN :creation_time_start AND :creation_time_end ");
        } else if (creationTimeStart != null){
            where.add(" AND creation_time >= :creation_time_start");
        }

        Map<String, Object> params = new HashMap<>();
        params.put("category", category);
        params.put("creation_time_start", creationTimeStart);
        params.put("creation_time_end", creationTimeEnd);

        String sql = ResourceUtils.resourceAsString(getClass(),
                "dao/sql_get_complaint.sql") + where;

        return getRecordsWithParams(sql, params);
    }

    private List<Complaint> getRecordsWithParams(String sql, Map<String, Object> params) {
        return jdbcTemplate.query(sql, params, createRowMapper());
    }

    public RowMapper<Complaint> createRowMapper() {
        return  (rs, rowNum) -> new Complaint(
                rs.getLong("complaint_id"),
                rs.getString("message"),
                rs.getString("address"),
                rs.getString("category"),
                rs.getTimestamp("creation_time")
        );
    }
}
