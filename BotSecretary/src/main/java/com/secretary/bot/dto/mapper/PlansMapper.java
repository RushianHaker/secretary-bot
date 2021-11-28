package com.secretary.bot.dto.mapper;

import com.secretary.bot.dto.Plans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс PlansMapper
 *
 * @author Max Ivanov
 * created 28.11.2021
 */

@Slf4j
public class PlansMapper implements RowMapper<Plans> {

    @Override
    public Plans mapRow(ResultSet rs, int rowNum) throws SQLException {
        var entity = new Plans(
                rs.getInt("id"),
                rs.getString("plan")
                );
        log.trace("mapRow(): entity = [{}]", entity);
        return entity;
    }
}
