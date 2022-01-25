package com.logs.kafka.consumer.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс PlansMapper - маппер модели ConsumerLog
 *
 * @author Max Ivanov
 * created 28.11.2021
 */
@Slf4j
public class ConsumerMapper implements RowMapper<ConsumerLog> {

    @Override
    public ConsumerLog mapRow(ResultSet rs, int rowNum) throws SQLException {
        var date = rs.getDate("date_time");
        var entity = new ConsumerLog(
                rs.getInt("id"),
                rs.getString("message"),
                rs.getString("topic"),
                date == null ? null : date.toLocalDate()
        );
        log.trace("ConsumerMapper(): entity = [{}]", entity);
        return entity;
    }
}