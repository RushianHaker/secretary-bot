package com.secretary.bot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * Класс AppConfig
 *
 * @author MaxIvanov
 * created 22.07.2021
 */
@Configuration
public class AppConfig {

    @Bean
    ObjectMapper customObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    TelegramBotsApi telegramBotsApi() throws TelegramApiException{
        return new TelegramBotsApi(DefaultBotSession.class);
    }
}
