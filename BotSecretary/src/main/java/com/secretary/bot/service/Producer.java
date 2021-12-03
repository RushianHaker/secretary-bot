package com.secretary.bot.service;

import com.secretary.bot.config.VertxKafkaConfig;
import com.secretary.bot.dto.User;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.kafka.client.producer.KafkaProducerRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Класс Producer - класс издатель, который отправляет сообщения в кафку
 *
 * @author Max Ivanov
 * created 08.11.2021
 */
@Service
@Slf4j
@AllArgsConstructor
public class Producer {

    private static final String TOPIC = "users";
    private final VertxKafkaConfig vertxConf;

    public void sendMessage(User user) {
        log.info("Producing message=[{}]", user);

        var producer = vertxConf.createProducer();
        producer.partitionsFor(TOPIC)
                .onSuccess(partitions -> partitions.forEach(System.out::println))
                .onFailure(cause -> log.error("Could not produce=" + cause.getMessage()));

        producerWrite(producer, user);
    }

    public void producerWrite(KafkaProducer<String, String> producer, User user) {
        KafkaProducerRecord<String, String> record =
                KafkaProducerRecord.create(TOPIC, " " + user.toString() + ", author-" + user.getName() +
                        ",  written on topic-" + TOPIC);

        producer.send(record)
                .onSuccess(recordMetadata -> log.info("Message written on " + recordMetadata.getTopic()
                        + ", offset=" + recordMetadata.getOffset() +
                        ", timestamp=" + recordMetadata.getTimestamp()))
                .onFailure(cause -> log.error("Could not producer write=" + cause.getMessage()));
    }
}