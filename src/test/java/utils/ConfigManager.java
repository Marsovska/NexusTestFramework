package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigManager {

    private static final Logger logger = LoggerFactory.getLogger(ConfigManager.class);

    private ConfigManager() {

    }

    public static BrowserType getBrowser() {
        String browser = ConfigReader.read("browser");

        try {
            return BrowserType.valueOf(browser.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.error("Invalid browser value in config.properties: {}", browser);
            throw new RuntimeException(
                    "Invalid browser specified in config.properties: " + browser +
                            ". Allowed values: CHROME, FIREFOX, EDGE"
            );
        }
    }
    public static String getApplicationUrl (){
        return ConfigReader.read("url");
    }
    public static String getUsername () {
        return ConfigReader.read("userName");
    }
    public static String getPassword () {
        return ConfigReader.read("password");
    }
}
