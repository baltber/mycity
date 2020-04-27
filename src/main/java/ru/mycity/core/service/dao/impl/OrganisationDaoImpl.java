package ru.mycity.core.service.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import ru.mycity.core.controller.exception.NotFoundException;
import ru.mycity.core.service.dao.IOrganisationDao;
import ru.mycity.core.service.dao.model.Complaint;
import ru.mycity.core.service.dao.model.Organisation;
import ru.mycity.core.service.dao.utils.ResourceUtils;

import java.util.List;

@Service
public class OrganisationDaoImpl implements IOrganisationDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public OrganisationDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public long save(Organisation user) {
        return 0;
    }

    @Override
    public long getIdByGuid(String guid) throws NotFoundException {
        String sql = ResourceUtils.resourceAsString(getClass(),"dao/organisation/sql_get_id_by_guid.sql");
        SqlParameterSource params = new MapSqlParameterSource("organisation_guid", guid);

        List<Long> res = jdbcTemplate.query(sql, params, createRowMapper("organisation_id"));

        if (res != null && res.size()>0){
            return res.get(0);
        } else{
            throw new NotFoundException("Организация не существует");
        }

    }

    @Override
    public long getProjectIdByGuid(String guid) throws NotFoundException {
        String sql = ResourceUtils.resourceAsString(getClass(),"dao/organisation/sql_get_project_id_by_guid.sql");
        SqlParameterSource params = new MapSqlParameterSource("organisation_guid", guid);

        List<Long> res = jdbcTemplate.query(sql, params, createRowMapper("project_id"));

        if (res != null && res.size()>0){
            return res.get(0);
        } else{
            throw new NotFoundException("Организация не существует");
        }

    }

    RowMapper<Long> createRowMapper(String field){
        return (rs, rowNum) -> rs.getLong(field);
    }


    @Override
    public String getConfigByGuid(String guid) {
        String sql = ResourceUtils.resourceAsString(getClass(),"dao/organisation/sql_get_config_by_guid.sql");
        SqlParameterSource params = new MapSqlParameterSource("organisation_guid", guid);

        return jdbcTemplate.queryForObject(sql, params, String.class);
    }


}
