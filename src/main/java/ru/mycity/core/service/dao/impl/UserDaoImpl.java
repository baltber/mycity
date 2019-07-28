package ru.mycity.core.service.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.mycity.core.service.dao.IUserDao;
import ru.mycity.core.service.dao.model.User;
import ru.mycity.core.service.dao.utils.ResourceUtils;

import java.sql.PreparedStatement;
import java.util.List;


@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserDaoImpl implements IUserDao {

    private final JdbcOperations jdbcOperations;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcOperations jdbcOperations, NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcOperations = jdbcOperations;
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<User> getUserByUserName(String userName){
        String sql = ResourceUtils.resourceAsString(getClass(),"dao/user/sql_get_user.sql");
        SqlParameterSource params = new MapSqlParameterSource("username", userName);

        return getWithParams(sql, params);
    }

    @Override
    public long addNewUser(String userName, String password, String role){
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = ResourceUtils.resourceAsString(getClass(),"dao/user/sql_insert_new_user.sql");
        jdbcOperations.update(psc-> {
            PreparedStatement ps = psc.prepareStatement(sql, new String[]{"user_id"});
            ps.setString(1, userName);
            ps.setString(2, password);
            ps.setString(3, role);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }


    private List<User> getWithParams(String sql, SqlParameterSource params) {
        return jdbcTemplate.query(sql, params, createRowMapper());
    }

    public RowMapper<User> createRowMapper() {
        return  (rs, rowNum) -> new User(
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("role")
        );
    }
}
