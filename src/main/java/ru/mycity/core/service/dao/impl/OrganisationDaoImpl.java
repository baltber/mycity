package ru.mycity.core.service.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import ru.mycity.core.service.dao.IOrganisationDao;
import ru.mycity.core.service.dao.model.Organisation;
import ru.mycity.core.service.dao.utils.ResourceUtils;

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
    public long getIdByGuid(String guid) {
        return 0;
    }

    @Override
    public String getConfigByGuid(String guid) {
        String sql = ResourceUtils.resourceAsString(getClass(),"dao/organisation/sql_get_config_by_guid.sql");
        SqlParameterSource params = new MapSqlParameterSource("organisation_guid", guid);

        return jdbcTemplate.queryForObject(sql, params, String.class);
    }


}
