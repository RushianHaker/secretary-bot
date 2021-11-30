package com.logs.kafka.consumer.config;

import io.vertx.core.Vertx;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
* Класс AppConfig
*
* @author Max Ivanov
* created 01.12.2021
*/
@Configuration
public class AppConfig {

    @Bean
    Vertx vertx() {
        return Vertx.vertx();
    }
}
