package org.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static Properties getPropertyObject() throws IOException {
        FileInputStream fp = new FileInputStream("src/test/resources/properties/login.properties");
        Properties prop = new Properties();
        prop.load(fp);
        return prop;
    }

    public static String getUsername() throws IOException {
        return getPropertyObject().getProperty("username");
    }

    public static String getPassword() throws IOException {
        return getPropertyObject().getProperty("password");
    }
}
