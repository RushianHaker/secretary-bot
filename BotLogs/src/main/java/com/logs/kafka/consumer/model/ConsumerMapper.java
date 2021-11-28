package com.logs.kafka.consumer.model;

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
public class ConsumerMapper implements RowMapper<ConsumerLog> {

    @Override
    public ConsumerLog mapRow(ResultSet rs, int rowNum) throws SQLException {
        var entity = new ConsumerLog(
                rs.getInt("id"),
                rs.getString("message"),
                rs.getString("uri"),
                rs.getString("date_time")
        );
        log.trace("ConsumerMapper(): entity = [{}]", entity);
        return entity;
    }
}