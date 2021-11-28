package com.secretary.bot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Класс DbException
 *
 * @author MaxIvanov
 * created 19.08.2021
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends BaseException {

    private final static String MESSAGE = "Not Found";

    public NotFoundException(Throwable t) {
        super(MESSAGE, t);
    }

    public NotFoundException() {
        super(MESSAGE);
    }
}
