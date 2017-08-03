package ru.progtools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.progtools.connection.ActiveLibraryPath;
import ru.progtools.exception.QueryCreatorException;
import ru.progtools.localization.ResourceLoader;
import ru.progtools.mssql.MsSqlQueryCreator;
import ru.progtools.properties.PropertiesKeys;
import ru.progtools.properties.PropertiesLoader;
import ru.progtools.servlet.SrvltAdminPage;
import ru.progtools.servlet.SrvltAdminTreatment;
import ru.progtools.servlet.SrvltGeneratorXml;
import ru.progtools.servlet.SrvltIndex;
import ru.progtools.servlet.SrvltServerMsSql;
import ru.progtools.servlet.SrvltServerPage;
import ru.progtools.xml.loader.ServersLoader;
import ru.progtools.xml.servers.ServersManager;
import ru.progtools.xml.servers.mssql.MsSql;

/**
 *
 * @author ezhov_da
 */
public class SqlServerDescription {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //загружаем dll-ки
        ActiveLibraryPath.setPath();
        //получаем список свойств
        PropertiesLoader propertiesLoader = new PropertiesLoader(PropertiesKeys.FILE_CONFIG);
        try {
            propertiesLoader.loadProperties();
        } catch (IOException ex) {
            Logger.getLogger(SqlServerDescription.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        //грузим локализацию приложения
        String localiization = propertiesLoader.getProperty(PropertiesKeys.LOCALIZATION);
        if ("".equals(localiization)) {
            ResourceLoader.getInstance().load(null);
        } else {
            String[] localiizationArray = localiization.split(" ");
            if (localiizationArray.length == 1) {
                ResourceLoader.getInstance().load(new Locale(localiizationArray[0]));
            } else {
                ResourceLoader.getInstance().load(
                        new Locale(
                                localiizationArray[0],
                                localiizationArray[1]
                        ));
            }
        }

        //получаем порт для запуска сервера
        int port = Integer.valueOf(propertiesLoader.getProperty(PropertiesKeys.SERVER_WEB_SERVER_PORT));

        ServersLoader serversLoader = new ServersLoader();
        serversLoader.loadServersManager(propertiesLoader.getProperty(PropertiesKeys.SERVER_SERVERS_CONFIG_FILE));
        //получаем список серверов
        ServersManager serversManager = serversLoader.getServersManager();

        SrvltIndex srvltIndex;
        try {
            srvltIndex = new SrvltIndex(serversManager, propertiesLoader);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SqlServerDescription.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        Server server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        //регистрируем сервлет index
        context.addServlet(new ServletHolder(srvltIndex), "/index");
        try {
            //регистрируем сервлет генерации xml
            context.addServlet(new ServletHolder(new SrvltGeneratorXml(propertiesLoader)), "/" + propertiesLoader.getProperty(PropertiesKeys.SERVER_HTTP_URL_GENERATOR));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SqlServerDescription.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            //регистрируем сервлет админки
            context.addServlet(new ServletHolder(new SrvltAdminPage(propertiesLoader)), "/" + propertiesLoader.getProperty(PropertiesKeys.SERVER_HTTP_URL_ADMIN));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SqlServerDescription.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            //регистрируем сервлет обработки админки
            context.addServlet(new ServletHolder(new SrvltAdminTreatment(propertiesLoader)), "/" + "adminTreatment");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SqlServerDescription.class.getName()).log(Level.SEVERE, null, ex);
        }

        //регистрируем сервера ms sql
        List<MsSql> listMsSql = serversManager.getMsSql();
        listMsSql.forEach(m -> {
            try {
                context.addServlet(new ServletHolder(new SrvltServerPage(m.getNameServer(), propertiesLoader)), "/" + m.getNameServer());
                String urlServletServer = "/" + propertiesLoader.getProperty(PropertiesKeys.SERVER_HTTP_URL_LOADER) + m.getNameServer();
                context.addServlet(new ServletHolder(new SrvltServerMsSql(new MsSqlQueryCreator(m), m)), urlServletServer);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SqlServerDescription.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SqlServerDescription.class.getName()).log(Level.SEVERE, null, ex);
            } catch (QueryCreatorException ex) {
                Logger.getLogger(SqlServerDescription.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("html");
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{
            resource_handler, context
        });
        server.setHandler(handlers);
        try {
            server.start();
            System.out.println("Listening port : " + port);
            server.join();
        } catch (Exception e) {
            System.out.println("Error.");
            e.printStackTrace();
        }
    }

}
