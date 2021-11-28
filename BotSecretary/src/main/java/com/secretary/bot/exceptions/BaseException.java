package com.secretary.bot.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс BaseExeption
 *
 * @author MaxIvanov
 * created 19.08.2021
 */
@Slf4j
public class BaseException extends RuntimeException{

    public BaseException(String msg, Throwable t) {
        super(msg, t);
        log.error(msg, t);
    }

    public BaseException(String msg) {
        super(msg);
        log.error(msg);
    }

}
