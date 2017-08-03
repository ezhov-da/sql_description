package ru.progtools.xml.loader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.util.Objects;
import ru.progtools.xml.servers.ServersManager;
import ru.progtools.xml.servers.mssql.Db;
import ru.progtools.xml.servers.mssql.MsSql;

/**
 * Загрузчик списка серверов
 *
 * @author deonisius
 */
public class ServersLoader {

    private static ServersLoader serversLoader;
    private ServersManager serversManager;

    public static void loadManager() {
        if (Objects.isNull(serversLoader)) {
            serversLoader = new ServersLoader();
        }
    }

    public synchronized ServersManager getServersManager() {
        return serversManager;
    }

    public synchronized void loadServersManager(String fileConfigServers) {
        XStream xStream = new XStream(new DomDriver());
        xStream.processAnnotations(ServersManager.class);
        xStream.processAnnotations(MsSql.class);
        xStream.processAnnotations(Db.class);
        serversManager = (ServersManager) xStream.fromXML(new File(fileConfigServers));
    }
}
