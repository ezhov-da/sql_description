package ru.progtools.properties;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Файл загрузчик настроек
 *
 * @author deonisius
 */
public class PropertiesLoader {

    private final PropertiesKeys fileConfig;
    private Properties properties;

    public PropertiesLoader(PropertiesKeys fileConfig) {
        this.fileConfig = fileConfig;
    }

    public void loadProperties() throws IOException {
        properties = new Properties();
        properties.load(new FileReader(new File(fileConfig.getKey())));
    }

    public String getProperty(PropertiesKeys key) {
        return properties.getProperty(key.getKey());
    }
}
