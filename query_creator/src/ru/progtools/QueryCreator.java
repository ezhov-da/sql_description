package ru.progtools;

import ru.progtools.exception.QueryCreatorException;

/**
 * Интерфейс для получения запроса с xml описанием объектов БД
 *
 * @author deonisius
 */
public interface QueryCreator {

    String getQuery() throws QueryCreatorException;
}
