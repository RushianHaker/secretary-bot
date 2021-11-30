package com.logs.kafka.consumer.repository;

import com.logs.kafka.consumer.model.ConsumerLog;
import org.h2.message.DbException;

import java.util.List;

/**
 * Интерфейс IUserRepository - описывает репозиторий работы с записями событий в БД
 *
 * @author Max Ivanov
 * created 08.11.2021
 */
public interface IConsumerLogRepository {

     /**
      * Возвращает список записей
      *
      * @return список всех записей
      * @throws DbException в случае ошибки БД
      */
     List<ConsumerLog> getLogsList();

     /**
      * Вставка новой записи
      *
      * @param entity новая запись
      * @throws DbException в случае ошибки БД
      */
     void insert(ConsumerLog entity);
}
