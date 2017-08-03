package ru.progtools.servlet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.progtools.FileFullReader;
import ru.progtools.localization.PageLocal;
import ru.progtools.properties.PropertiesKeys;
import ru.progtools.properties.PropertiesLoader;
import ru.progtools.xml.servers.Server;
import ru.progtools.xml.servers.ServersManager;
import ru.progtools.xml.servers.mssql.MsSql;

/**
 * Сервлет, который генерирует страницу index
 *
 * @author deonisius
 */
public class SrvltIndex extends HttpServlet {

    private final ServersManager serversManager;
    private final PropertiesLoader propertiesLoader;
    private String indexPage;

    public SrvltIndex(ServersManager serversManager, PropertiesLoader propertiesLoader) throws FileNotFoundException {
        this.serversManager = serversManager;
        this.propertiesLoader = propertiesLoader;
        generateIndexPage();
    }

    private void generateIndexPage() throws FileNotFoundException {
        List<MsSql> listServers = serversManager.getMsSql();
        FileFullReader fileFullReader = new FileFullReader(propertiesLoader.getProperty(PropertiesKeys.SERVER_HTTP_PAGE_INDEX));
        String fileIndex = fileFullReader.read();
        String hrefTmplate = propertiesLoader.getProperty(PropertiesKeys.SERVER_HTTP_HREF_TEMPLATE);
        String constUrl = propertiesLoader.getProperty(PropertiesKeys.SERVER_HTTP_CONSTANT_URL);
        String constNameServer = propertiesLoader.getProperty(PropertiesKeys.SERVER_HTTP_CONSTANT_SERVER_NAME);
        String constServersLinks = propertiesLoader.getProperty(PropertiesKeys.SERVER_HTTP_CONSTANT_SERVERS_LINKS);

        StringBuilder stringBuilder = new StringBuilder();
        listServers.forEach(s -> {
            Server server = (Server) s;
            String href = hrefTmplate.replaceAll(constUrl, server.getNameServer());
            href = href.replaceAll(constNameServer, server.getNameServer());
            stringBuilder.append(href);

        });
        indexPage = fileIndex.replaceAll(constServersLinks, stringBuilder.toString());
        String constUrlGenerator = propertiesLoader.getProperty(PropertiesKeys.SERVER_HTTP_CONSTANT_URL_GENERATOR);
        String urlGenerator = propertiesLoader.getProperty(PropertiesKeys.SERVER_HTTP_URL_GENERATOR);

        indexPage = indexPage.replaceAll(constUrlGenerator, urlGenerator);
        String constUrlAdmin = propertiesLoader.getProperty(PropertiesKeys.SERVER_HTTP_CONSTANT_URL_ADMIN);
        String urlAdmin = propertiesLoader.getProperty(PropertiesKeys.SERVER_HTTP_URL_ADMIN);
        indexPage = indexPage.replaceAll(constUrlAdmin, urlAdmin);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PageLocal pageLocal = new PageLocal(indexPage);
        String localizePage = pageLocal.localize();
        response.getOutputStream().write(localizePage.getBytes());
        response.setContentType("text/html; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
