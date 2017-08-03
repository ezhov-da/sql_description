/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.progtools.localization;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Загрузчик ресурсов
 * @author deonisius
 */
public class ResourceLoader {

    private static ResourceLoader resourceLoader;
    private ResourceBundle resourceBundle;

    public synchronized void load(Locale locale) {
        String classLoc = ResourceLoader.class.getPackage().getName() + ".properties.localization";
        if (Objects.isNull(locale)) {
            resourceBundle = ResourceBundle.getBundle(classLoc, Locale.getDefault());
        } else {
            resourceBundle = ResourceBundle.getBundle(classLoc, locale);
        }
    }

    public String getString(LocalKeys key) {
        if (Objects.isNull(resourceBundle)) {
            load(null);
        }
        return resourceBundle.getString(key.getKey());
    }

    public static final ResourceLoader getInstance() {
        if (Objects.isNull(resourceLoader)) {
            resourceLoader = new ResourceLoader();
        }
        return resourceLoader;
    }

}
