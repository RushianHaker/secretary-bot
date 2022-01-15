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
 * Класс UsersController - простенький контроллер для получения списка мыслей из БД
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
    @RequestMapping(path = "/users_idea", method = RequestMethod.GET)
    public List<User> getIdeaList() {
        log.debug("Method - getIdeaList was called");
        return userService.getUserList();
    }
}



