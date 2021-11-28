package com.logs.kafka.consumer.repository;

import com.logs.kafka.consumer.config.DbConfig;
import com.logs.kafka.consumer.model.ConsumerLog;
import io.reactivex.Single;
import io.vertx.core.json.JsonArray;
import io.vertx.reactivex.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.Metadata;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;


/**
 * Класс DbRepository - пример JDBC-репозитория для работы с БД
 *
 * @author Max Ivanov
 */
@Slf4j
@Repository
public class DbRepository extends SqlClientHolder {

    private static final String CREATE_TABLE =
            "create table log " +
                    "(" +
                    "id serial," +
                    "uri varchar(49)," +
                    "message varchar(1000)," +
                    "date_time varchar(30)," +
                    ")";

    private static final String SQL_INSERT_ROW =
            "insert into log (uri, message, date_time) values " +
                    "(?, ?, cast(? as date)) RETURNING id";

    private static final String SQL_SELECT =
            "select * " +
                    "from log where " +
                    "date_time between cast(? as date) and cast(? as date) " +
                    "order by date_time asc";


    /**
     * All-args constructor
     */
    public DbRepository(Vertx vertx, DbConfig.SpringDataJdbcProperties properties) {
        super(vertx, properties);
    }

    @PostConstruct
    void initDb() {
        execute(CREATE_TABLE)
                .subscribe(
                        () -> log.info("DB created"),
                        error -> log.error("DB creation failed", error)
                );
    }

    /**
     * Save a {@link Metadata} instance in DB
     */
    public Single<Long> insert(ConsumerLog request) {
        return queryWithParams(SQL_INSERT_ROW,
                new JsonArray()
                        .add(request.getUri())
                        .add(request.getMsg())
                        .add(request.getLogDate()))
                .map(resultSet -> resultSet.getRows().get(0).getLong("id"))
                .doOnSuccess(id -> log.debug("Saved metadata=[{}] with id=[{}]", request, id));
    }

    /**
     * Query data from DB
     */
    public Single<List<ConsumerLog>> getList() {
        return queryWithParams(SQL_SELECT, new JsonArray())
                .map(resultSet -> resultSet.getRows())
                .toObservable()
                .flatMapIterable(list -> list)
                .map(json -> new ConsumerLog(
                        json.getInteger("id"),
                        json.getString("uri"),
                        json.getString("message"),
                        json.getString("date_time")
                ))
                .toList()
                .doOnSuccess(list -> log.trace("ConsumerLog created: [{}]", list));
    }

}
