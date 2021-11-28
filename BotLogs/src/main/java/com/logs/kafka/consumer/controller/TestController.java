package com.logs.kafka.consumer.controller;

import com.logs.kafka.consumer.service.Consumer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Класс TestController
 *
 * @author Max Ivanov
 * created 16.09.2021
 */

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/kafka")
public class TestController {

    private final Consumer consumerService;

    /**
     * Возвращает записи элемента из таблицы логов подписчика
     *
     */
    @GetMapping(value = "/log_list")
    public String getLogList() {
        log.trace("[GET] getLogList()");
        return consumerService.consumeLog().toString();
    }
}