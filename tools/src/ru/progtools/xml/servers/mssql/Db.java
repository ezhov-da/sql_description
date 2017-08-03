package ru.progtools.xml.servers.mssql;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * База данных
 *
 * @author deonisius
 */
@XStreamAlias("db")
public class Db {

    private String nameDb;
    private String nameSchema;

    public Db() {
    }

    public Db(String nameDb, String nameSchema) {
        this.nameDb = nameDb;
        this.nameSchema = nameSchema;
    }

    public String getNameDb() {
        return nameDb;
    }

    public void setNameDb(String nameDb) {
        this.nameDb = nameDb;
    }

    public String getNameSchema() {
        return nameSchema;
    }

    public void setNameSchema(String nameSchema) {
        this.nameSchema = nameSchema;
    }

}
