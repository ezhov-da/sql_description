package ru.progtools.xml.servers;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.ArrayList;
import java.util.List;
import ru.progtools.xml.servers.mssql.MsSql;

/**
 * Менеджер серверов
 *
 * @author deonisius
 */
@XStreamAlias("serversManager")
public class ServersManager {

    private List<MsSql> mssql;

    public ServersManager() {
        mssql = new ArrayList<>();
    }

    public List<MsSql> getMsSql() {
        return mssql;
    }

    public void addMsSql(MsSql msSql) {
        this.mssql.add(msSql);
    }

    public void setMsSql(List<MsSql> listMsSql) {
        this.mssql = listMsSql;
    }
}
