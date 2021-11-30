package com.logs.kafka.consumer.service;

import com.logs.kafka.consumer.config.VertxKafkaConfig;
import com.logs.kafka.consumer.model.ConsumerLog;
import com.logs.kafka.consumer.repository.IConsumerLogRepository;
import io.vertx.kafka.client.common.TopicPartition;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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

    private final IConsumerLogRepository consumerLogRepo;
    private final VertxKafkaConfig vertxConf;

    @KafkaListener(topics = TOPIC_NAME, groupId = "group_id")
    public void consumeWriting(String message) {
        var consumer = vertxConf.createConsumer();
        TopicPartition topicPartition = new TopicPartition().setTopic(TOPIC_NAME);

        consumer.assign(topicPartition)
                .onSuccess(v -> log.info("#### Partition successful assigned [topic={}]", TOPIC_NAME))
                .compose(v -> consumer.assignment())
                .onSuccess(partition -> {
                            log.info("#### Consumed received [topic={}, message={}]", message, TOPIC_NAME);
                            var entity = new ConsumerLog(
                                    0, message, TOPIC_NAME, LocalDate.now());
                            consumerLogRepo.insert(entity);
                            log.info("#### Inserted entity [entity={}]", message);
                        }
                )
                .onFailure(cause -> System.out.println("Could not subscribe=" + cause.getMessage()));
    }

    public List<ConsumerLog> consumeLog() {
        var list = consumerLogRepo.getLogsList();
        list.forEach(msg -> log.info("#### Consumer list log [{}]", msg.toString()));
        return list;
    }
}
