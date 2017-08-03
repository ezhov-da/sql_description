package ru.progtools.mssql;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import ru.progtools.exception.QueryCreatorException;
import ru.progtools.xml.servers.mssql.Db;
import ru.progtools.xml.servers.mssql.MsSql;

/**
 *
 * @author deonisius
 */
public class MsSqlQueryCreatorTest {

    public MsSqlQueryCreatorTest() {
    }

    @Test
    public void testSomeMethod() {
        MsSql msSql = new MsSql();

        Db db = new Db("TEST", "dbo");
        msSql.addDatabases(db);
        db = new Db("OTZ", "dbo");
        msSql.addDatabases(db);

        MsSqlQueryCreator msSqlQueryCreator = new MsSqlQueryCreator(msSql);
        try {
            msSqlQueryCreator.getQuery();
        } catch (QueryCreatorException ex) {
            Logger.getLogger(MsSqlQueryCreatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
