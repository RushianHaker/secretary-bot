package com.logs.kafka.consumer.config;

import io.vertx.core.Vertx;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Класс AppConfig - конфигурация
 *
 * @author Max Ivanov
 * created 28.11.2021
 */
@Configuration
public class AppConfig {

    @Bean
    Vertx vertx() {
        return Vertx.vertx();
    }
}
