﻿## Проект: "Создание описания сущностей БД"

Данный проект в первую очередь предназначен для БД MS SQL и позволяет развернуть WEB сервер  для просмотра уже внесенных описаний,
генерирования описания, а так же изменения уже существующих.

### Структура проекта:
Проект          | Описание
----------------|----------------------
localization    | Локализация проекта
query_creator   | Генерирование запроса к БД для получения списка описаний
server		    | Основной проект WEB сервера
tools           | Инструменты для работы, такие как загрузчик настроек и т.д
