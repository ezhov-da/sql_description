package ru.progtools;

import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.Test;

/**
 *
 * @author deonisius
 */
public class LocalizationTest {

    @Test
    public void testLoc() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ru.progtools.loc.properties.localization", Locale.ENGLISH);
        String text = resourceBundle.getString("title.index.servers.list");
        System.out.println(text);
        resourceBundle = ResourceBundle.getBundle("ru.progtools.loc.properties.localization", new Locale("ru", "RU"));
        text = resourceBundle.getString("title.index.servers.list");
        System.out.println(text);
    }
}
