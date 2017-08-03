package ru.progtools.servlet;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.progtools.AppListsCreator;
import ru.progtools.localization.PageLocal;
import ru.progtools.properties.PropertiesKeys;
import ru.progtools.properties.PropertiesLoader;

/**
 * Обработка и изменение файла с выпадающим списком
 *
 * @author deonisius
 */
public class SrvltAdminTreatment extends HttpServlet {

    private final PropertiesLoader propertiesLoader;

    public SrvltAdminTreatment(PropertiesLoader propertiesLoader) throws FileNotFoundException {
        this.propertiesLoader = propertiesLoader;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pass = req.getParameter("pass");
        PageLocal pageLocal;
        String localizePage;
        String adminPass = propertiesLoader.getProperty(PropertiesKeys.SERVER_ADMIN_PASS);
        if (!adminPass.equals(pass)) {
            pageLocal = new PageLocal("title.admin.panel.error.pass");
            localizePage = pageLocal.localize();
            resp.getOutputStream().write(localizePage.getBytes());
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        String status = req.getParameter("status");
        String tool = req.getParameter("tool");
        String author = req.getParameter("author");

        try (FileWriter fileWriter = new FileWriter("html/templates/app_lists.json");) {
            fileWriter.write(AppListsCreator.createJson(status, tool, author));
            fileWriter.flush();
            pageLocal = new PageLocal("title.admin.panel.changes.commit");
            localizePage = pageLocal.localize();
            resp.getOutputStream().write(localizePage.getBytes());
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
