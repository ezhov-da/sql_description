package ru.progtools.xml.servers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.junit.Test;
import ru.progtools.xml.servers.mssql.Db;
import ru.progtools.xml.servers.mssql.MsSql;

/**
 *
 * @author deonisius
 */
public class ServersManagerTest {

    public ServersManagerTest() {
    }

    @Test
    public void testSomeMethod() {
        XStream xStream = new XStream(new DomDriver());
        xStream.processAnnotations(ServersManager.class);
        xStream.processAnnotations(MsSql.class);
        xStream.processAnnotations(Db.class);

        MsSql msSql = new MsSql("EZHOVMSSQL", "com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://EZHOV\\MSSQL;database=test;user=sa;password=adminadmin;");
        Db db = new Db("TEST", "dbo");
        msSql.addDatabases(db);

        ServersManager serversManager = new ServersManager();
        serversManager.addMsSql(msSql);

        String string = xStream.toXML(serversManager);
        System.out.println(string);
    }

}
