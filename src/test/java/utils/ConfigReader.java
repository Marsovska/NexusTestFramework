package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();
    static {
        try (FileInputStream fis = new FileInputStream(Constants.CONFIG_FILE_PATH)){
            properties.load(fis);
        }catch (IOException e) {
            throw new RuntimeException("Failed to load config file from: "+ Constants.CONFIG_FILE_PATH, e);
        }
    }
    private ConfigReader() {

    }
    public static String read (String key) {
        String value = properties.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            throw new RuntimeException("Missing or empty property in config file: " + key);
        }
        return value.trim();
    }
}

