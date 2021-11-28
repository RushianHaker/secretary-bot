package com.logs.kafka.consumer.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Класс Json - утилитный класс для логирования
 *
 * @author dkupchenko
 * created 23.09.2020
 */
public class Json {
    static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Encode instance as JSON
     *
     * @param obj instance
     * @return JSON
     */
    public static String encode(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return obj.toString();
        }
    }

    public static <T> T decode(String json, Class<T> clazz) throws JsonProcessingException {
        return mapper.readValue(json, clazz);
    }

}
