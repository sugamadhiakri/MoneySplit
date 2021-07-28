package com.moneysplit.api.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.moneysplit.api.model.User;

import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        var user = new User();
        user.setId(rs.getLong("user_id"));
        user.setName(rs.getString("name"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));

        return user;
    }
}
