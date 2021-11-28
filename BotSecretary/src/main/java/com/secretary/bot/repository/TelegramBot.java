package com.secretary.bot.repository;

import com.secretary.bot.dto.User;
import com.secretary.bot.service.Producer;
import com.secretary.bot.service.UserService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Класс TelegramBot
 *
 * @author MaxIvanov
 * created 30.07.2021
 */
@Slf4j
@Getter
@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final Producer producerService;
    private final UserService userService;

    private final String botUsername;
    private final String botToken;

    public TelegramBot(
            TelegramBotsApi telegramBotsApi,
            @Value("${telegram-bot.name}") String botUsername,
            @Value("${telegram-bot.token}") String botToken,
            Producer producerService, UserService userService) throws TelegramApiException {
        this.botUsername = botUsername;
        this.botToken = botToken;
        this.producerService = producerService;
        this.userService = userService;

        telegramBotsApi.registerBot(this);
    }

    /**
     * Этот метод вызывается при получении обновлений через метод GetUpdates.
     *
     * @param request Получено обновление
     */
    @Override
    public void onUpdateReceived(Update request) {
        SendMessage response = new SendMessage();
        Message requestMessage = request.getMessage();
        response.setChatId(requestMessage.getChatId().toString());
        try {
            if (request.hasMessage() && requestMessage.hasText())
                log.trace("Working onUpdateReceived, request text[{}]", request.getMessage().getText());

            if (requestMessage.getText().equals("/start")) {
                response.setText(
                        "Напишите команду для показа списка идей: \n " +
                                "/idea  - показать заметку \n");
                execute(response);
            } else if (requestMessage.getText().equals("/idea")) {
                if (userService.getUserList().isEmpty()) {
                    response.setText("Идея не найдена, попробуйте еще раз. \n");
                    execute(response);
                } else {
                    for (User txt : userService.getUserList()) {
                        response.setText(txt.toString());
                        execute(response);
                    }
                }
            } else {
                response.setText("Я запомнил вашу мысль, просто продолжайте отправлять их в чат :) \n ");
                execute(response);
            }
            log.trace("Working, text[{}]", requestMessage.getText());
            producerService.sendMessage(requestMessage.getMessageId(), requestMessage.getChat().getUserName(),
                    requestMessage.getText(), requestMessage.getText());
            userService.insert(new User(requestMessage.getMessageId(), requestMessage.getChat().getUserName(),
                    requestMessage.getText(), requestMessage.getText()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
