package com.logs.kafka.consumer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Класс ConsumerLog
 *
 * @author Max Ivanov
 * created 22.11.2021
 */
@Data
@RequiredArgsConstructor
public class ConsumerLog {

    @JsonProperty("id")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private final int id;

    @JsonProperty("message")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private final String msg;

    @JsonProperty("logDate")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private final LocalDateTime logDate;

    @Override
    public String toString() {
        return "Was added log [id=" + id + ", log=" + msg + logDate.toString() + "]";
    }
}
