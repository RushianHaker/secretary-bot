
# Secretary Telegram Bot
____

**Telegram Bot** - используется для отслеживания всех мыслей и вывода их в чат

> Мой Телеграм - https://t.me/l_i_m_k_o_r_n

---

- Bot - [@your_private_secretary_bot](t.me/your_private_secretary_bot)

---

## Introduction

Сервис помогает нам строить планы и
получать актуальную информацию об изменениях в сегодняшних мыслях и заботах

## Requirements

Для использования сервиса требуется:

### Requirements > Сборка

* JDK 14 и выше
* Проприетарные библиотеки telegrambots-abilities, telegrambots 
* Настроенный экземпляр БД PostgreSQL

## Installation

Данный проект использует Spring Boot, поэтому дополнительную исчерпывающую информацию можно получить из документации
проектов Spring и Spring Boot. 

Минимальный перечень необходимых для запуска проекта действий указан далее.

### Installation > Сборка

* Поместить файлы проекта в отдельную папку

`git clone https://github.com/RushianHaker/secretary-bot.git`

## Installation > Конфигурирование

### Installation > Конфигурирование > Настройка БД PostgreSQL

На основе SQL-скриптов в БД PostgreSQL суперпользователем:

* создать схему БД (см. [tables_init.sql](sql/tables_init.sql))


### Installation > Конфигурирование > Настройки проекта gitlab-webhook

Основные настройки проекта указаны в файле [application.properties](src/main/resources/application.properties).
Для переопределения настроек проекта необходимо обратиться к
[документации Spring](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-external-config).
Один из вариантов переопределения настроек проекта:

* В папке проекта на сервере создать папку `config`
* Скопировать в папку `config` файл настроек проекта [application.properties](src/main/resources/application.properties)
* Изменить содержимое файла настроек в соответствии с окружением сервера

## Installation > Запуск

* Переопределить необходимые настройки сервиса (настройка логирование и пр)

* Запустить проект
