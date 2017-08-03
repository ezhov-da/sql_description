package ru.progtools.servlet;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.progtools.FileFullReader;
import ru.progtools.localization.LocalKeys;
import ru.progtools.localization.PageLocal;
import ru.progtools.localization.ResourceLoader;
import ru.progtools.properties.PropertiesKeys;
import ru.progtools.properties.PropertiesLoader;

/**
 * * Сервлет, который отвечает за отображение страницы сервера
 *
 * @author deonisius
 */
public class SrvltServerPage extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final String serverName;
    private final PropertiesLoader propertiesLoader;
    private String page;

    public SrvltServerPage(String serverName, PropertiesLoader propertiesLoader) throws FileNotFoundException {
        this.serverName = serverName;
        this.propertiesLoader = propertiesLoader;
        generatePage();
    }

    private void generatePage() throws FileNotFoundException {
        String constServerName = propertiesLoader.getProperty(PropertiesKeys.SERVER_HTTP_CONSTANT_SERVER_NAME);
        String constLoader = propertiesLoader.getProperty(PropertiesKeys.SERVER_HTTP_CONSTANT_LOADER);
        String varLoader = propertiesLoader.getProperty(PropertiesKeys.SERVER_HTTP_URL_LOADER);
        FileFullReader fileFullReader = new FileFullReader(propertiesLoader.getProperty(PropertiesKeys.SERVER_HTTP_PAGE_SERVER));
        String pageTemplate = fileFullReader.read();
        page = pageTemplate.replaceAll(constServerName, serverName);
        page = page.replaceAll(constLoader, varLoader);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PageLocal pageLocal = new PageLocal(page);
        String localizePage = pageLocal.localize();
        response.getOutputStream().write(localizePage.getBytes());
        response.setContentType("text/html; charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
