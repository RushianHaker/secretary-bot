package com.logs.kafka.consumer.config;

import io.vertx.core.Vertx;
import io.vertx.kafka.client.consumer.KafkaConsumer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс VertxKafkaConfig - конфигурация vertx-kafka
 *
 * @author Max Ivanov
 * created 28.11.2021
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class VertxKafkaConfig {

    private final Vertx vertx;

    public KafkaConsumer<String, String> createConsumer() {
        Map<String, String> config = new HashMap<>();
        config.put("bootstrap.servers", "localhost:9092");
        config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("group.id", "group_id");
        config.put("auto.offset.reset", "earliest");
        config.put("enable.auto.commit", "false");

        log.info("Created consumer [{},{}]", vertx.toString(), config);
        return KafkaConsumer.create(vertx, config);
    }
}
