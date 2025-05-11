package org.epam.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private static PropertyReader propertyReader;
    private Properties properties;

    private PropertyReader() {
    }

    public static PropertyReader getEnvProperties() {
        if (propertyReader == null) {
            propertyReader = new PropertyReader();
            String environment = SystemProperties.getProperty("environment");
            if (StringUtils.isBlank(environment)) {
                propertyReader.readProperties("src/test/resources/prod.properties");
            } else {
                propertyReader.readProperties(String.format("src/test/resources/%s.properties", environment));
            }
        }
        return propertyReader;
    }

    public String getProperty(String name) {
        return properties.getProperty(name);
    }

    private void readProperties(String path) {
        properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
