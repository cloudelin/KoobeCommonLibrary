package com.koobe.common.core.config.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * Read Koobe Application Properties from User Home Directory
 *
 * @author lyhcode
 */
public class UserHomePropertyConfigReader extends PropertyBasedConfigReader implements ConfigReader {

    public final static String USER_HOME = System.getProperty("user.home");

    public final static String PROPERTY_FILE_NAME = ".koobe.properties";

    /**
     *
     * @throws java.io.IOException
     */
    public UserHomePropertyConfigReader() throws IOException {
        File propertyFile = new File(USER_HOME, PROPERTY_FILE_NAME);
        doRead(new FileInputStream(propertyFile));
    }

    /**
     *
     * @param propertyInputStream
     * @throws java.io.IOException
     */
    private void doRead(InputStream propertyInputStream) throws IOException {
        Properties srcProps = new Properties();
        srcProps.load(propertyInputStream);
        
        Properties props = new Properties();
        
        for (String key : srcProps.stringPropertyNames()) {
            String value = srcProps.getProperty(key);
            props.setProperty(key, value);
        }
        
        setProperties(props);
    }
}
