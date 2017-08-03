package ru.progtools.servlet;

import javax.servlet.http.HttpServlet;
import ru.progtools.QueryCreator;

/**
 * Сервлет отвечающий за отображение страницы с комментариями для каждого сервера
 *
 * @author deonisius
 */
public abstract class SqlServlet extends HttpServlet {
    protected final QueryCreator queryCreator;

    public SqlServlet(QueryCreator queryCreator) {
        this.queryCreator = queryCreator;
    }
    
}
