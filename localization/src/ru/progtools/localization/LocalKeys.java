package ru.progtools.localization;

/**
 * Ключи для файла локализации
 *
 * @author deonisius
 */
public enum LocalKeys {
    TITLE_COMMON_LOCAL_JS("title.common.local.js"),
    //
    TITLE_INDEX_SERVERS_LIST("title.index.servers.list"),
    TITLE_INDEX_SERVERS_TITLE("title.index.servers.title"),
    TITLE_INDEX_DESCRIPTION_GENERATOR("title.index.description.generator"),
    TITLE_INDEX_TEXT_OPEN_GENERATOR("title.index.text.open.generator"),
    TITLE_INDEX_TEXT_ADMIN_PANEL("title.index.text.admin.panel"),
    TITLE_INDEX_TEXT_OPEN_ADMIN_PANEL("title.index.text.open.admin.panel"),
    //
    TITLE_SERVER_SERVER("title.server.server"),
    TITLE_SERVER_TABLE_LOCAL_URL("title.server.table.local.url"),
    TITLE_SERVER_TABLE_COLUMN_NAME("title.server.table.column.name"),
    TITLE_SERVER_TABLE_COLUMN_TOOL("title.server.table.column.tool"),
    TITLE_SERVER_TABLE_COLUMN_STATUS("title.server.table.column.status"),
    TITLE_SERVER_TABLE_COLUMN_AUTHOR("title.server.table.column.author"),
    TITLE_SERVER_TABLE_COLUMN_DATE_CREATE("title.server.table.column.date.create"),
    //
    TITLE_GENERATOR_DESCRIPTION_GENERATOR("title.generator.description.generator"),
    TITLE_GENERATOR_STATUS("title.generator.status"),
    TITLE_GENERATOR_TOOL("title.generator.tool"),
    TITLE_GENERATOR_AUTHOR("title.generator.author"),
    TITLE_GENERATOR_NAME("title.generator.name"),
    TITLE_GENERATOR_LIST_PARAMS("title.generator.list.params"),
    TITLE_GENERATOR_LIST_FIELDA("title.generator.list.fields"),
    TITLE_GENERATOR_COMMENT("title.generator.comment"),
    TITLE_GENERATOR_LIST_LOGS("title.generator.list.logs"),
    TITLE_GENERATOR_RESULT_DESCRIPTION_GENERATION("title.generator.result.description.generation"),
    TITLE_GENERATOR_BUTTON_GENERATE("title.generator.button.generate"),
    TITLE_GENERATOR_BUTTON_CLEAR("title.generator.button.clear"),
    TITLE_GENERATOR_BUTTON_FROM_XML("title.generator.button.from.xml"),
    //
    TITLE_DETAIL_DESCRIPTION_TITLE("title.detail.description.title"),
    TITLE_DETAIL_DESCRIPTION_COMMENT("title.detail.description.comment"),
    TITLE_DETAIL_DESCRIPTION_PARAMS("title.detail.description.params"),
    TITLE_DETAIL_DESCRIPTION_FIELDS("title.detail.description.fields"),
    TITLE_DETAIL_DESCRIPTION_LOGS("title.detail.description.logs"),
    //
    TITLE_ADMIN_PANEL_PANEL_TITLE("title.admin.panel.title"),
    TITLE_ADMIN_PANEL_STATUS("title.admin.panel.status"),
    TITLE_ADMIN_PANEL_TOOLS("title.admin.panel.tools"),
    TITLE_ADMIN_PANEL_AUTHORS("title.admin.panel.authors"),
    TITLE_ADMIN_PANEL_PASSWORD("title.admin.panel.password"),
    TITLE_ADMIN_PANEL_BUTTON_PASSWORD("title.admin.panel.button.password"),
    TITLE_ADMIN_PANEL_ERRPR_PASS("title.admin.panel.error.pass"),
    TITLE_ADMIN_PANEL_CHANGES_COMMIT("title.admin.panel.changes.commit");
    
    private final String key;

    private LocalKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
