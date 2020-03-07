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
    public List<User> getUserByLogin(String login){
        String sql = ResourceUtils.resourceAsString(getClass(),"dao/user/sql_get_user.sql");
        SqlParameterSource params = new MapSqlParameterSource("login", login);

        return getWithParams(sql, params);
    }

    @Override
    public long save(User user){
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = ResourceUtils.resourceAsString(getClass(),"dao/user/sql_insert_new_user.sql");
        jdbcOperations.update(psc-> {
            PreparedStatement ps = psc.prepareStatement(sql, new String[]{"user_id"});
            int i = 1;
            ps.setString(i++, user.getFirstName());
            ps.setString(i++, user.getLastName());
            ps.setString(i++, user.getAddress());
            ps.setInt(i++, user.getFlat());
            ps.setString(i++, user.getLogin());
            ps.setString(i++, user.getPassword());
            ps.setString(i++, user.getLocation());
            ps.setString(i++, user.getRole());
            ps.setString(i++, user.getUserId());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }


    private List<User> getWithParams(String sql, SqlParameterSource params) {
        return jdbcTemplate.query(sql, params, createRowMapper());
    }

    public RowMapper<User> createRowMapper() {
        return  (rs, rowNum) -> new User(
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("address"),
                rs.getInt("flat"),
                rs.getString("login"),
                rs.getString("password"),
                rs.getString("location"),
                rs.getString("role"),
                rs.getString("user_guid")

        );
    }
}
