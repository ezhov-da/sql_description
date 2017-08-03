package ru.progtools.localization;

import java.util.Arrays;
import java.util.List;

/**
 * Локализатор страниц, через него пропускаем все страницы и он локализует :)
 *
 * @author deonisius
 */
public class PageLocal {

    private String page;

    public PageLocal(String page) {
        this.page = page;
    }

    public String localize() {
        ResourceLoader resourceLoader = ResourceLoader.getInstance();
        List<LocalKeys> list = Arrays.asList(LocalKeys.values());
        list.forEach(lk -> {
            String key = lk.getKey();
            page = page.replace(key, resourceLoader.getString(lk));
        });

        return page;
    }
}
