package ru.progtools.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.progtools.QueryCreator;
import ru.progtools.connection.ConnectionDb;
import ru.progtools.exception.QueryCreatorException;
import ru.progtools.xml.loader.DescriptionConverter;
import ru.progtools.xml.servers.mssql.MsSql;

/**
 * Сервлет, который отвечает за сервер MS SQL
 */
public class SrvltServerMsSql extends SqlServlet {

    private static final long serialVersionUID = 1L;
    private final MsSql msSql;
    private String query;
    
    public SrvltServerMsSql(QueryCreator queryCreator, MsSql msSql) throws ClassNotFoundException, QueryCreatorException {
        super(queryCreator);
        this.msSql = msSql;
        query = queryCreator.getQuery();
        Class.forName(msSql.getClassDriver());
    }

    String ss;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ConnectionDb connectionDb = new ConnectionDb();
        List<String> list = connectionDb.getListData(msSql.getConnectionUrl(), query);
        DescriptionConverter createDescriptionFromString = new DescriptionConverter();
        List<String> strings = new ArrayList<>();

        list.forEach(s -> {
            String json = createDescriptionFromString.getJsonDescription(s);
            strings.add(json);

        });

        String objects = Arrays.toString(strings.toArray());

        String finalStr = "{\"data\":" + objects + "}";
        finalStr = finalStr.replaceAll("\"@", "\"");

        //System.out.println(finalStr);
        response.getOutputStream().write(finalStr.getBytes());
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
