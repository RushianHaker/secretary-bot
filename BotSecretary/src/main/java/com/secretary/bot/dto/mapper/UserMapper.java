package com.secretary.bot.dto.mapper;

import com.secretary.bot.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс PlansMapper - маппер модели User
 *
 * @author Max Ivanov
 * created 28.11.2021
 */

@Slf4j
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        var entity = new User(
                rs.getInt("id"),
                rs.getString("user_name"),
                rs.getString("description")
                );
        log.trace("mapRow(): entity = [{}]", entity);
        return entity;
    }
}
