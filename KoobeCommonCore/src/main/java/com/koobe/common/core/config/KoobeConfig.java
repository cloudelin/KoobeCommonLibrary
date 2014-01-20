/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koobe.common.core.config;

import com.koobe.common.core.config.reader.SystemEnvironmentConfigReader;
import com.koobe.common.core.config.reader.ConfigReader;
import com.koobe.common.core.config.reader.GradlePropertyConfigReader;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Properties;

import com.koobe.common.core.config.reader.UserHomePropertyConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author lyhcode
 */
public class KoobeConfig {
    
    private static final Logger log = LoggerFactory.getLogger(KoobeConfig.class);

    // Implements singleton instance
    private static KoobeConfig instance = null;

    /**
     * Fetch Config object instance (Singleton)
     *
     * @return KoobeConfig
     */
    public static KoobeConfig getInstance() {
        return instance == null ? instance = new KoobeConfig() : instance;
    }

    private final Properties config = new Properties();

    {
        applyConfigReader(new SystemEnvironmentConfigReader());

        try {
            applyConfigReader(new UserHomePropertyConfigReader());
        }
        catch (IOException ex) {
            log.info("Koobe Application Config {}/{} not found",
                    UserHomePropertyConfigReader.USER_HOME,
                    UserHomePropertyConfigReader.PROPERTY_FILE_NAME);
        }
    }

    /**
     * Apply reader and read all config values
     *
     * @param reader
     */
    public void applyConfigReader(ConfigReader reader) {
        Properties newConfig = reader.readConfigValues();
        
        for (Object okey : newConfig.keySet()) {
            String key = okey.toString();
            config.put(key, newConfig.getProperty(key));
        }
    }
    
    /**
     * Apply gradle.properties for configurations
     * 
     * @param propFile
     * @throws IOException 
     */
    public void applyGradlePropertyConfig(File propFile) throws IOException {
        applyConfigReader(new GradlePropertyConfigReader(propFile));
    }

    /**
     * Retrieve config value by name
     *
     * @param name Config name
     * @return Config value
     */
    public String get(String name) {
        return config.getProperty(name);
    }

    /**
     * Dump all config values
     */
    public void printAllConfigValues() {
        System.out.println("----- Koobe Config Values -----");
        for (Object okey : config.keySet()) {
            String key = okey.toString();
            System.out.printf("%s=%s\n", key, config.getProperty(key));
        }
        System.out.println("----- End -----");
    }
    
    /**
     *
     * @return amazon web services access_key
     */
    public String getAwsAccessKeyID() {
        return config.getProperty("AWS_ACCESS_KEY_ID");
    }
    
    /**
     *
     * @return amazon web services secret_access_key
     */
    public String getAwsSecretKey() {
        return config.getProperty("AWS_SECRET_KEY");
    }
    
    /**
     *
     * @return amazon web services elastic cache url
     */
    public String getAwsElastiCacheUrl() {
        String configValue = config.getProperty("AWS_ELASTI_CACHE_URL");
        if (configValue == null) {
            log.warn("System environment variable not configured: AWS_ELASTI_CACHE_URL");
        }
        return configValue;
    }
    
    private final static String DEFAULT_AWS_ELASTICACHE_HOST = "localhost";
    private final static int DEFAULT_AWS_ELASTICACHE_PORT = 11211;

    /**
     * Get AWS ElastiCache Hostname
     * 
     * @return hostname of cache server
     */
    public String getAwsElastiCacheHost() {
        String url = getAwsElastiCacheUrl();
        String host = DEFAULT_AWS_ELASTICACHE_HOST;
        
        if (url == null) {
            return host;
        }
        
        int pos = url.indexOf(":");
        
        if (pos >= -1) {
            host = url.substring(0, pos);
        }
        else if (!url.isEmpty()) {
            host = url;
        }
        
        return host;
    }
    
    /**
     * Get AWS ElastiCache Port
     * 
     * @return port no (default: 11211)
     */
    public int getAwsElastiCachePort() {
        String url = getAwsElastiCacheUrl();
        int port = DEFAULT_AWS_ELASTICACHE_PORT;
        
        if (url == null) {
            return port;
        }

        int pos = url.indexOf(":");

        if (pos >= -1) {
            String _port = url.substring(pos + 1);
            port = Integer.parseInt(_port);
        }
        
        return port;
    }

    /**
     * Get socket address instace for specified elasticache
     * 
     * @return socket address instance
     */
    public InetSocketAddress getAwsElastiCacheSocketAddress() {
        String host = getAwsElastiCacheHost();
        int port = getAwsElastiCachePort();
        
        log.info("Create InetSocketAddress {} : {}", host, port);
        
        return new InetSocketAddress(host, port);
    }
    
    /**
     * Get JDBC Connection URL String
     * 
     * @return connection url
     */
    public String getJDBCConnectionUrl() {
        String configValue = config.getProperty("JDBC_CONNECTION_STRING");
        
        if (configValue == null) {
            log.warn("System environment variable not configured: JDBC_CONNECTION_STRING");
        }
        
        return configValue;
    }
    
    public String getDefaultSqsQueueUrl() {
    	String configValue = config.getProperty("AWS_SQS_QUEUE_URL");
        if (configValue == null) {
            log.warn("System environment variable not configured: AWS_SQS_QUEUE_URL");
        }
        return configValue;
    }
}
