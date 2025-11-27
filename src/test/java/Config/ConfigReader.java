package Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties props = new Properties();

    static {
        try {
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            props.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load config.properties file", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
