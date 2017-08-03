package ru.progtools;

/**
 * Создание объекта для выпадающих списков
 *
 * @author deonisius
 */
public class AppListsCreator {

    private static final String TEMPLATE = "﻿{\n"
            + "    \"statusList\": [%s],\n"
            + "    \"toolList\": [%s],\n"
            + "    \"whoList\": [%s]\n"
            + "}";

    public static synchronized String createJson(String status, String tools, String author) {
        return String.format(TEMPLATE, replace(status), replace(tools), replace(author));
    }

    private static String replace(String source) {
        String result = "\"" + source.replaceAll(",", "\",\"") + "\"";

        return result;
    }
}
