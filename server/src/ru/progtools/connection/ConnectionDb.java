package ru.progtools.connection;

import java.util.List;
import org.sql2o.Sql2o;
import org.sql2o.Connection;

/**
 * Класс, который обрабатывает подключения
 *
 * @author deonisius
 */
public class ConnectionDb {

    public List<String> getListData(String urlConnection, String query) {
        List<String> dataBeans;
        Sql2o sql2o = new Sql2o(urlConnection, null, null);
        try (Connection connection = sql2o.open();) {
            dataBeans = connection.createQuery(query).executeAndFetch(String.class);
        }
        return dataBeans;
    }
}
