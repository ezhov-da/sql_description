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
 *
 * @author deonisius
 */
public class SrvltGeneratorXml extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final PropertiesLoader propertiesLoader;
    private final String page;

    public SrvltGeneratorXml(PropertiesLoader propertiesLoader) throws FileNotFoundException {
        this.propertiesLoader = propertiesLoader;
        FileFullReader fileFullReader = new FileFullReader(propertiesLoader.getProperty(PropertiesKeys.SERVER_HTTP_PAGE_GENERATOR_XML));
        page = fileFullReader.read();
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
