package com.moneysplit.api.dao;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import com.moneysplit.api.dao.mapper.UserMapper;
import com.moneysplit.api.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends AbstractMySqlDAO {

    @Autowired
    public UserDAO(DataSource dataSource) {
        super(dataSource);
        // TODO Auto-generated constructor stub
    }

    public List<User> getAllUsers() {
        return findAll("getUser", new UserMapper());
    }

    public User createUser(final User user) {
        MapSqlParameterSource params = toParamSource("name", user.getName()).addValue("username", user.getUsername())
                .addValue("password", user.getPassword()).addValue("email", user.getEmail());

        var keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(toSqlString("createUser", params), params, keyHolder);
        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    public void updateUser(User user) {
        MapSqlParameterSource params = toParamSource("user_id", user.getId()).addValue("name", user.getName())
                .addValue("username", user.getUsername()).addValue("password", user.getPassword())
                .addValue("email", user.getEmail());

        jdbcTemplate.update(toSqlString("updateUser", params), params);

    }

    public Optional<User> findUserById(long id) {
        return findById("findUserById", toParamSource("user_id", id), new UserMapper());
    }

    public void deleteUserById(long userId) {
        MapSqlParameterSource params = toParamSource("user_id", userId);
        jdbcTemplate.update(toSqlString("deleteUserById", params), params);
    }
}
