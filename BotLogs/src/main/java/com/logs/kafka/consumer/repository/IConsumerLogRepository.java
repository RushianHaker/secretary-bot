package com.logs.kafka.consumer.repository;

import com.logs.kafka.consumer.model.ConsumerLog;

import java.util.List;

/**
 * Интерфейс IUserRepository
 *
 * @author Max Ivanov
 * created 08.11.2021
 */

public interface IConsumerLogRepository {

     /**
      * Возвращает записи элемента из таблицы логов подписчика
      *
      */
     List<ConsumerLog> getLogsList();

     /**
      * Заполняет записи элементами из приходящего топика логов
      *
      */
     void insert(ConsumerLog entity);
}
