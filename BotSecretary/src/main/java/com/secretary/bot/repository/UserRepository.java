package com.secretary.bot.repository;

import com.secretary.bot.dto.User;
import com.secretary.bot.dto.mapper.UserMapper;
import com.secretary.bot.exceptions.DbException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Класс UserRepository реализует репозиторий работы с записями событий в БД
 *
 * @author IvanovMaxim
 * created 12.08.2021
 */
@Slf4j
@Repository
public class UserRepository implements IUserRepository {

    // constants
    private static final String SQL_SELECT_BY_NAME = "" +
            "SELECT id, user_name, description FROM user_table WHERE id=?";
    private static final String SQL_SELECT_LIST = "" +
            "SELECT id, user_name, description FROM user_table";
    private static final String SQL_INSERT = "" +
            "INSERT INTO user_table (user_name, description) VALUES (?, ?)";
    private static final String SQL_DELETE = "" +
            "DELETE FROM user_table WHERE id = ?";

    protected final static UserMapper USER_MAPPER = new UserMapper();

    // beans
    protected final JdbcTemplate template;


    /**
     * Req-args constructor for Spring DI
     */
    public UserRepository(@Qualifier("bot-db") JdbcTemplate template) {
        this.template = template;
    }

    /**
     * Возвращает список записей по id
     *
     * @return запрашиваемая запись
     * @throws DbException в случае ошибки БД
     */
    @Override
    public User getById(int id) throws DbException {
        try {
            return DataAccessUtils.singleResult(
                    template.query(SQL_SELECT_BY_NAME, USER_MAPPER, id));
        } catch (DataAccessException exception) {
            throw new DbException(exception);
        }
    }

    /**
     * Возвращает список записей
     *
     * @return запрашиваемая запись
     * @throws DbException в случае ошибки БД
     */
    @Override
    public List<User> getUserList() throws DbException {
        try {
            return template.query(SQL_SELECT_LIST, USER_MAPPER);
        } catch (DataAccessException exception) {
            throw new DbException(exception);
        }
    }

    /**
     * Вставка новой записи
     *
     * @param entity новая запись
     * @throws DbException в случае ошибки БД
     */
    @Override
    public void insert(User entity) throws DbException {
        try {
            // В параметры запроса все поля сущности кроме идентификатора, т.к. он serial и генерируется автоматом
            var result = template.update(SQL_INSERT,
                    entity.getName(),
                    entity.getDescription());
            if (result != 1) log.trace("UserRepository.update() with {} rows inserted", entity);
            log.trace("insert({}) result={}", entity, result);
        } catch (DataAccessException exception) {
            throw new DbException(exception);
        }
    }

    /**
     * Удаление записи
     *
     * @param entity удаляемая запись
     * @throws DbException в случае ошибки БД
     */
    @Override
    public void delete(User entity) throws DbException {
        try {
            var result = template.update(SQL_DELETE, entity.getId());
            if (result != 1) log.trace("UserRepository.delete() with {} rows inserted", entity);
            log.trace("delete({}) result={}", entity, result);
        } catch (DataAccessException exception) {
            throw new DbException(exception);
        }
    }
}
