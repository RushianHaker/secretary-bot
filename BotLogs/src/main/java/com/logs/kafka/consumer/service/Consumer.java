package com.logs.kafka.consumer.service;

import com.logs.kafka.consumer.model.ConsumerLog;
import com.logs.kafka.consumer.repository.IConsumerLogRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Класс Consumer - класс подписчик, который получает сообщения из кафки и обрабатывает их
 *
 * @author Max Ivanov
 * created 08.11.2021
 */
@Slf4j
@Service
@AllArgsConstructor
public class Consumer {
    private static final String TOPIC_NAME = "users";
    protected final IConsumerLogRepository consumerRepo;

    /**
     * Метод обработки сообщений от producer,
     * который "отлавливает" эти самые сообщения с помощью аннотации KafkaListener и принимает их в виде параметра.
     *
     * @param message сообщение от producer, которое прилетает в кафка
     */
    @KafkaListener(topics = TOPIC_NAME, groupId = "group_id")
    public void consumeWriting(String message) {
        var consumerLog = new ConsumerLog(0, message, TOPIC_NAME, LocalDate.now());
        consumerRepo.insert(consumerLog);
        log.info("#### Consumed received message [{}]", message);
    }

    /**
     * Получение списка логов из БД
     */
    public List<ConsumerLog> consumeLog() {
        var list = consumerRepo.getLogsList();
        list.forEach(msg -> log.info("#### Consumer list log [{}]", msg.toString()));
        return list;
    }
}
