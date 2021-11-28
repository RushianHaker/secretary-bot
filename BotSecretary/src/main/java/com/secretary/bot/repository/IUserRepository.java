package com.secretary.bot.repository;

import org.h2.message.DbException;
import com.secretary.bot.dto.User;

import java.util.List;

/**
 * Интерфейс IUserRepository описывает репозиторий работы с записями событий в БД
 *
 * @author IvanovMaxim
 * created 12.08.2021
 */
public interface IUserRepository {

    /**
     * Возвращает список записей по id
     *
     * @return запрашиваемая запись
     * @throws DbException в случае ошибки БД
     */
    User getById(int id);

    /**
     * Возвращает список записей
     *
     * @return список всех записей
     * @throws DbException в случае ошибки БД
     */
    List<User> getUserList();

    /**
     * Вставка новой записи
     *
     * @param entity новая запись
     * @throws DbException в случае ошибки БД
     */
    void insert(User entity);

    /**
     * Удаление записи
     *
     * @param entity удаляемая запись
     * @throws DbException в случае ошибки БД
     */
    void delete(User entity);
}
