package com.secretary.bot.service;

import com.secretary.bot.dto.User;
import com.secretary.bot.repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Класс Producer - класс издатель, который отправляет сообщения в кафку
 *
 * @author Max Ivanov
 * created 08.11.2021
 */
@Service
@Slf4j
public class Producer {

    private static final String TOPIC = "users";
    protected final IUserRepository repo;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public Producer(IUserRepository repo) {
        this.repo = repo;
    }

    public void sendMessage(User user) {
        if (user.getName() == null || user.getDescription().isEmpty()) log.info("#### Empty name/description message");
        log.trace("#### Producing message [user={}]", user);
        kafkaTemplate.send(TOPIC, "Writing in log -> " + user);
    }
}