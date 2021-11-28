package com.logs.kafka.consumer.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Класс DbConfig
 *
 * @author Max Ivanov
 * created 09.08.2021
 */
@Configuration
public class DbConfig extends DefaultDbConfig {

    @Bean
    @Qualifier("demo")
    @ConfigurationProperties(prefix = "app.db.demo")
    SpringDataJdbcProperties demoJdbcProperties() {
        return new SpringDataJdbcProperties();
    }

    @Bean
    @Qualifier("demo")
    public DataSource demoDataSource(@Qualifier("demo") SpringDataJdbcProperties properties) {
        return hikariDataSource("db", properties);
    }

    @Bean
    @Qualifier("demo")
    JdbcTemplate demoJdbcTemplate(@Qualifier("demo") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Data
    @NoArgsConstructor
    public static class SpringDataJdbcProperties {

        // constants
        private static final String H2_DATABASE_DRIVER = "org.h2.Driver";

        /**
         * JDBC URL property
         */
        String url;
        /**
         * JDBC driver class name property
         */
        String driver;
        /**
         * JDBC username property
         */
        String user;
        /**
         * JDBC password property
         */
        String password;
        /**
         * Hikari / Vertica maxPoolSize property
         */
        String poolSize;
        /**
         * Minimum pool size
         */
        int minPoolSize = 4;
        /**
         * Maximum pool size
         */
        int maxPoolSize = 10;
        /**
         * This property controls the maximum amount of time (in milliseconds) that a connection is allowed to
         * sit idle in the pool. A value of 0 means that idle connections are never removed from the pool.
         */
        long idleTimeout;
        /**
         * This property controls the maximum lifetime of a connection in the pool. When a connection
         * reaches this timeout, even if recently used, it will be retired from the pool.
         * An in-use connection will never be retired, only when it is idle will it be removed
         */
        long maxLifetime;
        /**
         * Bulk insert size
         */
        Integer bulkSize;


        /**
         * All-args constructor for {@link SpringDataJdbcProperties#toString()} (logging)
         *
         * @param url JDBC driver class name property
         * @param driver JDBC driver class name property
         * @param user JDBC username property
         * @param password JDBC password property
         * @param poolSize Hikari / Vertica maxPoolSize property
         * @param bulkSize bulk insert size
         */
        public SpringDataJdbcProperties(
                String url, String driver, String user, String password, String poolSize, Integer bulkSize) {
            this.url = url;
            this.driver = driver;
            this.user = user;
            this.password = password;
            this.poolSize = poolSize;
            this.bulkSize = bulkSize;
        }


        /**
         * Возвращает истину, если экземпляр описывает in-memory H2 database
         *
         * @return истина, если экземпляр описывает in-memory H2 database
         */
        public boolean isH2Database() {
            return driver.equals(H2_DATABASE_DRIVER);
        }

        /**
         * Возвращает строковое представление экземпляра объекта в формате JSON
         *
         * @return строковое представление экземпляра объекта в формате JSON
         */
        @Override
        public String toString() {
            var props = new SpringDataJdbcProperties(
                    url, driver, user, ((password == null) || password.isEmpty()) ? "" : "*****", poolSize, bulkSize);
            return Json.encode(props);
        }

    }

}
