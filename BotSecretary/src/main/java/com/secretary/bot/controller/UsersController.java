package com.secretary.bot.controller;

import com.secretary.bot.dto.User;
import com.secretary.bot.exceptions.NotFoundException;
import com.secretary.bot.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Класс UsersController
 *
 * @author MaxIvanov
 * created 13.07.2021
 */
@Slf4j
@RestController
@RequestMapping("${app.http.bot")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class UsersController {

    private final UserService userService;

    /**
     * Возвращает список пользователей и связанных с ними планами
     */
    @RequestMapping(path = "/users_plan", method = RequestMethod.GET)
    public List<User> getPlansList() {
        log.debug("Method - getPlansList was called");
        return wrapResult(userService.getUserList());
    }

    /**
     * Обёртка результата
     *
     * @param result результат
     * @return результат
     * @throws NotFoundException если результат null
     */
    public <T> T wrapResult(T result) {
        if (result == null)
            throw new NotFoundException();
        return result;
    }

}



