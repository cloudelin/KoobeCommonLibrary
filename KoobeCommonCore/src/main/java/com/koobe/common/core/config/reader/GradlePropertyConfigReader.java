/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.koobe.common.core.config.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author lyhcode
 */
public class GradlePropertyConfigReader extends PropertyBasedConfigReader implements ConfigReader {
    private final static String PROP_KEY_PREFIX = "systemProp.SYSENV_";

    /**
     *
     * @param propertyFile
     * @throws IOException
     */
    public GradlePropertyConfigReader(File propertyFile) throws IOException {
        this(new FileInputStream(propertyFile));
    }
    
    /**
     *
     * @param propertyInputStream
     * @throws IOException
     */
    public GradlePropertyConfigReader(InputStream propertyInputStream) throws IOException {
        Properties srcProps = new Properties();
        srcProps.load(propertyInputStream);
        
        Properties props = new Properties();
        
        for (Object okey : srcProps.keySet()) {
            String key = okey.toString();
            
            if (key.startsWith(PROP_KEY_PREFIX)) {
                props.setProperty(key.replaceFirst(PROP_KEY_PREFIX, ""), srcProps.getProperty(key));
            }
        }
        
        setProperties(props);
    }
}
