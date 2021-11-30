package com.secretary.bot.config;

import io.vertx.core.Vertx;
import io.vertx.kafka.client.producer.KafkaProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

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

    public KafkaProducer<String, String> createProducer() {
        Properties config = new Properties();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put(ProducerConfig.ACKS_CONFIG, "1");

        log.info("Created consumer [{},{}]", vertx.toString(), config);
        return KafkaProducer.create(vertx, config);
    }
}
