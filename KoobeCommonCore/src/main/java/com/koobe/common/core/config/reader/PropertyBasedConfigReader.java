package com.koobe.common.core.config.reader;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lyhcode
 */
public abstract class PropertyBasedConfigReader implements ConfigReader {

    /**
     *
     */
    protected final static String[] _keywords = {
        "AWS_ACCESS_KEY_ID", // AWS Access Key ID
        "AWS_SECRET_KEY", // AWS Secret Access Key
        "JDBC_CONNECTION_STRING", // JDBC Connection String to RDS
        "AWS_SQS_QUEUE_URL", // AWS SQS Queue URL
        "PARAM1", // ?
        "PARAM2", // ElastiCache Memcached Host:Port
        "PARAM3", // S3 Book Path
        "PARAM4" // Deprecated: Amazon SQS server url
    };

    /**
     *
     */
    protected final static String[] _fix_names = {
        "PARAM2", "AWS_ELASTI_CACHE_URL", // PARAM2 => AWS_ELASTI_CACHE_URL
        "PARAM4", "AWS_SQS_QUEUE_URL" // PARAM4 => AWS_SQS_QUEUE_URL
    };

    /**
     *
     */
    protected static final Map<String, String> _fix_names_map = new HashMap<String, String>();

    static {
        // Generate static names map
        for (int i = 0; i < _fix_names.length; i += 2) {
            _fix_names_map.put(_fix_names[i], _fix_names[i + 1]);
        }
    }
    
    private Properties props;
    
    /**
     *
     * @param props properties
     */
    protected void setProperties(Properties props) {
        this.props = props;
    }
    
    /**
     *
     * @return properties
     */
    protected Properties getProperties() {
        return props;
    }

    /**
     * Access Config key/value paris form system environment variables
     * @return configs from system properties
     */
    @Override
    public Properties readConfigValues() {
        Properties result = new Properties();
        
        for (String key : _keywords) {
            try {
                String value = props.getProperty(key);
                if (value != null && !value.isEmpty()) {
                    
                    String realKey = key;
                    
                    // Fix deprecated keys
                    if (_fix_names_map.keySet().contains(key)) {
                        realKey = _fix_names_map.get(key);
                    }
                    
                    result.put(realKey, value);
                }
            }
            catch (Exception ex) {
                // nothing
            }
        }
        
        return result;
    }
}
