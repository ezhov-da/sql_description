package ru.progtools.xml.servers.mssql;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.ArrayList;
import java.util.List;
import ru.progtools.xml.servers.Server;

/**
 * Сервер MS SQL
 *
 * @author deonisius
 */
@XStreamAlias("server")
public class MsSql extends Server {

    private List<Db> databases;

    public MsSql() {
        databases = new ArrayList<>();
    }

    public MsSql(String nameServer, String classDriver, String connectionUrl) {
        this();
        super.nameServer = nameServer;
        super.classDriver = classDriver;
        super.connectionUrl = connectionUrl;
    }

    public List<Db> getDatabases() {
        return databases;
    }

    public void addDatabases(Db db) {
        databases.add(db);
    }

    public void setDatabases(List<Db> databases) {
        this.databases = databases;
    }
}
