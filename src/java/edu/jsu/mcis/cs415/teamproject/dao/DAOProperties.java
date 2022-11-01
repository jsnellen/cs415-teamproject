package edu.jsu.mcis.cs415.teamproject.dao;

import java.io.*;
import java.util.Properties;

public class DAOProperties {

    private static final String PROPERTIES_FILE = "dao.properties";
    private static final Properties PROPERTIES = new Properties();

    private final String prefix;

    static {

        try {

            InputStream file = DAOProperties.class.getResourceAsStream(PROPERTIES_FILE);
            PROPERTIES.load(file);

        } catch (IOException e) {
            throw new DAOException(e.getMessage());
        }

    }

    public DAOProperties(String prefix) {

        this.prefix = prefix;

    }

    public String getProperty(String key) {

        String fullKey = prefix + "." + key;
        String property = PROPERTIES.getProperty(fullKey);

        if (property == null || property.trim().length() == 0) {
            property = null;
        }

        return property;

    }

}
