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
 * Класс Consumer
 *
 * @author Max Ivanov
 * created 08.11.2021
 */
@Slf4j
@Service
@AllArgsConstructor
public class Consumer {
    protected final IConsumerLogRepository consumerRepo;

    @KafkaListener(topics = "users", groupId = "group_id")
    public void consumeWriting(String message) {
        var consumerLog = new ConsumerLog(message.length(), message, log.getName(), LocalDate.now().toString());
        consumerRepo.insert(consumerLog);
        log.info("#### Consumed received message [{}]", message);
    }

    public List<ConsumerLog> consumeLog() {
        var list = consumerRepo.getLogsList();
        list.forEach(msg -> log.info("#### Consumer list log [{}]", msg.toString()));
        return list;
    }
}
