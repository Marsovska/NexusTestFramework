package utils;

public class Constants {
    //*I added a private constructor so nobody accidentally creates an object of Constants
    private Constants() {
    }

    public static final String CONFIG_FILE_PATH =
            System.getProperty("user.dir") + "/src/test/resources/config/config.properties";
    public static final int IMPLICIT_WAIT = 10;
    public static final int EXPLICIT_WAIT = 20;

}
