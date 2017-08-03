package ru.progtools.xml.servers;

/**
 * Основной класс, который описывает общие свойтва для серверов
 *
 * @author deonisius
 */
public abstract class Server {
    protected String nameServer;
    protected String classDriver;
    protected String connectionUrl;

    public String getNameServer() {
        return nameServer;
    }

    public void setNameServer(String nameServer) {
        this.nameServer = nameServer;
    }

    public String getClassDriver() {
        return classDriver;
    }

    public void setClassDriver(String classDriver) {
        this.classDriver = classDriver;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }
}
