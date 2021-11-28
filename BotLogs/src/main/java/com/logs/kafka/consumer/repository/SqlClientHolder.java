package com.logs.kafka.consumer.repository;

import com.logs.kafka.consumer.config.DbConfig;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.ResultSet;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.jdbc.JDBCClient;
import io.vertx.reactivex.ext.sql.SQLClient;


/**
 * Класс SqlClientHolder инициализирует экземпляр {@link SQLClient} и содержит методы работы с БД
 *
 * @author Max Ivanov
 */
public class SqlClientHolder {

    // locals
    final SQLClient sqlClient;


    /**
     * Создаёт экземпляр {@link SQLClient}
     */
    public SqlClientHolder(Vertx vertx, DbConfig.SpringDataJdbcProperties properties) {
        sqlClient = JDBCClient.create(
                vertx,
                new JsonObject()
                        .put("url", properties.getUrl())
                        .put("driver_class", properties.getDriver())
                        .put("user", properties.getUser())
                        .put("password", properties.getPassword())
                        .put("max_pool_size", properties.getPoolSize())
                        .put("max_idle_time", properties.getIdleTimeout()));
    }

    /**
     * Execute SQL
     *
     * @param sql SQL for execute
     * @return instance of {@link ResultSet} with SQL result
     */
    public Completable execute(String sql) {
        return sqlClient
                .rxGetConnection()
                .flatMapCompletable(connection -> connection.rxExecute(sql)
                        .doOnEvent(error -> connection.close()));
    }

    /**
     * Execute SQL with params
     *
     * @param sql    SQL for execute
     * @param params params for SQL
     * @return instance of {@link ResultSet} with SQL result
     */
    public Single<ResultSet> queryWithParams(String sql, JsonArray params) {
        return sqlClient
                .rxGetConnection()
                .flatMap(connection -> connection.rxQueryWithParams(sql, params)
                        .doOnEvent((event, error) -> connection.close()));
    }

}
