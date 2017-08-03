package ru.progtools.properties;

/**
 * Список ключей настройки приложений
 *
 * @author deonisius
 */
public enum PropertiesKeys {
    FILE_CONFIG("config.properties"),
    LOCALIZATION("localization"),
    //
    SERVER_SERVERS_CONFIG_FILE("servers.congig.file"),
    SERVER_WEB_SERVER_PORT("web.server.port"),
    SERVER_HTTP_PAGE_INDEX("http.page.index"),
    SERVER_HTTP_PAGE_SERVER("http.page.server"),
    SERVER_HTTP_PAGE_GENERATOR_XML("http.page.generator.xml"),    
    SERVER_HTTP_HREF_TEMPLATE("http.href.template"),
    SERVER_HTTP_CONSTANT_URL("http.constant.url"),
    SERVER_HTTP_CONSTANT_SERVER_NAME("http.constant.server.name"),
    SERVER_HTTP_CONSTANT_SERVERS_LINKS("http.constant.servers.link"),
    SERVER_HTTP_URL_LOADER("http.url.loader"),
    SERVER_HTTP_CONSTANT_LOADER("http.constant.loader"),
    
    SERVER_HTTP_CONSTANT_URL_GENERATOR("http.constant.url.generator"),
    SERVER_HTTP_URL_GENERATOR("http.url.generator"),
    SERVER_HTTP_CONSTANT_URL_ADMIN("http.constant.url.admin"),
    SERVER_HTTP_URL_ADMIN("http.url.admin"),    
    SERVER_HTTP_PAGE_ADMIN("http.page.admin"),    
    SERVER_ADMIN_PASS("http.admin.pass"),       
     //
    QUERY_CREATOR_MSSQL_SCRIPT_BASIC("mssql.script.basic"),
    QUERY_CREATOR_MSSQL_SCRIPT_DB("mssql.script.db"),
    QUERY_CREATOR_MSSQL_CONSTANT_DB("mssql.constant.db"),
    QUERY_CREATOR_MSSQL_CONSTANT_SCHEMA("mssql.constant.schema"),
    QUERY_CREATOR_MSSQL_TEXT_CONCAT_DB("mssql.text.concat.db"),
    QUERY_CREATOR_MSSQL_CONSTANT_UNION_SCRIPT("mssql.constant.union.script");
    //

    private final String key;

    private PropertiesKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
