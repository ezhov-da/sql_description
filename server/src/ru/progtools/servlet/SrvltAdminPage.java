package ru.progtools.servlet;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.progtools.FileFullReader;
import ru.progtools.localization.PageLocal;
import ru.progtools.properties.PropertiesKeys;
import ru.progtools.properties.PropertiesLoader;

/**
 * Отображение страницы администрирования
 *
 * @author deonisius
 */
public class SrvltAdminPage extends HttpServlet {

    private final PropertiesLoader propertiesLoader;
    private String adminPage;

    public SrvltAdminPage(PropertiesLoader propertiesLoader) throws FileNotFoundException {
        this.propertiesLoader = propertiesLoader;
        generateIndexPage();
    }

    private void generateIndexPage() throws FileNotFoundException {
        FileFullReader fileFullReader = new FileFullReader(propertiesLoader.getProperty(PropertiesKeys.SERVER_HTTP_PAGE_ADMIN));
        adminPage = fileFullReader.read();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PageLocal pageLocal = new PageLocal(adminPage);
        String localizePage = pageLocal.localize();
        response.getOutputStream().write(localizePage.getBytes());
        response.setContentType("text/html; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
