package com.logs.kafka.consumer.handler;

import com.logs.kafka.consumer.model.ConsumerLog;
import com.logs.kafka.consumer.repository.DbRepository;
import com.sun.jdi.InternalException;
import io.reactivex.Completable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Класс SampleHandler
 *
 * @author Max Ivanov
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SampleHandler {

    // constants
    public static final String VALID_TYPE = "logsmetadata";
    // beans
    final DbRepository dbRepository;
    @Value("test.service.maxRecords")
    String maxRecords;


    /**
     * Обработка входящего запроса
     *
     * @param requestMetadata входящий запрос
     * @throws InternalException в случае исключений, связанных с отказом инфраструктуры (Kafka, DB etc.)
     */
    public Completable process(ConsumerLog requestMetadata) {
            log.info("requestMetadata=[{}]", requestMetadata.toString());
        return Completable.fromAction(() -> dbRepository.insert(requestMetadata));
    }

    /**
     * Возвращает тип обрабатываемых метаданных
     *
     * @return тип обрабатываемых метаданных
     */
    public String getMetadataType() {
        return VALID_TYPE;
    }

}
