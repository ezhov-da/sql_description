package ru.progtools.mssql;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.progtools.FileFullReader;
import ru.progtools.QueryCreator;
import ru.progtools.exception.QueryCreatorException;
import ru.progtools.properties.PropertiesKeys;
import ru.progtools.properties.PropertiesLoader;
import ru.progtools.xml.servers.mssql.Db;
import ru.progtools.xml.servers.mssql.MsSql;

/**
 * Класс по получению запроса для MS SQL
 *
 * @author deonisius
 */
public class MsSqlQueryCreator implements QueryCreator {

    private final MsSql msSql;

    public MsSqlQueryCreator(MsSql msSql) {
        this.msSql = msSql;
    }

    @Override
    public String getQuery() throws QueryCreatorException {
        PropertiesLoader propertiesLoader = new PropertiesLoader(PropertiesKeys.FILE_CONFIG);
        try {
            propertiesLoader.loadProperties();
        } catch (IOException ex) {
            Logger.getLogger(MsSqlQueryCreator.class.getName()).log(Level.SEVERE, null, ex);
            throw new QueryCreatorException(ex);
        }
        String pathToBasicScript = propertiesLoader.getProperty(PropertiesKeys.QUERY_CREATOR_MSSQL_SCRIPT_BASIC);
        String pathToDbScript = propertiesLoader.getProperty(PropertiesKeys.QUERY_CREATOR_MSSQL_SCRIPT_DB);
        FileFullReader fileFullReader;
        String textBasicCreate;
        String textDbCreate;
        try {
            fileFullReader = new FileFullReader(pathToBasicScript);
            textBasicCreate = fileFullReader.read();
            fileFullReader = new FileFullReader(pathToDbScript);
            textDbCreate = fileFullReader.read();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MsSqlQueryCreator.class.getName()).log(Level.SEVERE, null, ex);
            throw new QueryCreatorException(ex);
        }

        String constDbReplace = propertiesLoader.getProperty(PropertiesKeys.QUERY_CREATOR_MSSQL_CONSTANT_DB);
        String constSchemaReplace = propertiesLoader.getProperty(PropertiesKeys.QUERY_CREATOR_MSSQL_CONSTANT_SCHEMA);
        String constUnion = propertiesLoader.getProperty(PropertiesKeys.QUERY_CREATOR_MSSQL_CONSTANT_UNION_SCRIPT);
        String textConcatDb = propertiesLoader.getProperty(PropertiesKeys.QUERY_CREATOR_MSSQL_TEXT_CONCAT_DB);
        //System.out.println(textBasicCreate);
        //System.out.println(textDbCreate);

        //1 генерируем список баз
        List<Db> dbs = msSql.getDatabases();
        int size = dbs.size();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            String text = textDbCreate.replaceAll(constDbReplace, dbs.get(i).getNameDb());
            text = text.replaceAll(constSchemaReplace, dbs.get(i).getNameSchema());
            stringBuilder.append(text);
            if (i != size - 1) {
                stringBuilder.append(textConcatDb);
            }
        }

        //2 генерируем основной запрос для получения
        String basicSelect = textBasicCreate.replaceAll(constUnion, stringBuilder.toString());
        //System.out.println(basicSelect);

        return basicSelect;
    }

}
