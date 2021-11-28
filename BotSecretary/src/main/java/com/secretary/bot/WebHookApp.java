package com.secretary.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * Класс WebHookApp
 *
 * @author MaxIvanov
 * created 20.07.2021
 */

@Slf4j
@SpringBootApplication
public class WebHookApp {
    public static void main(String[] args) {
        SpringApplication.run(WebHookApp.class, args);
    }
}
