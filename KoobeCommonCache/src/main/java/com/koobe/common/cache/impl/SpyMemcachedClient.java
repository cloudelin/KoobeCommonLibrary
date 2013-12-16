/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.koobe.common.cache.impl;

import com.koobe.common.cache.CacheClient;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.FailureMode;
import net.spy.memcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author lyhcode
 */
public class SpyMemcachedClient implements CacheClient {
    
    private static final Logger log = LoggerFactory.getLogger(SpyMemcachedClient.class);

    private boolean available = true;
    
    private MemcachedClient client;
    
    /**
     * Default TTL is one hour
     */
    private final static int DEFAULT_TTL = 3600;
        
    public SpyMemcachedClient(InetSocketAddress address) {

        log.info("Create MemcachedClient client {}", address);
        
        // Connection Factory
        
        ConnectionFactoryBuilder cfb=new ConnectionFactoryBuilder();  
        cfb.setFailureMode(FailureMode.Redistribute);  
        cfb.setDaemon(true);  
        cfb.setProtocol(ConnectionFactoryBuilder.Protocol.TEXT);  

        cfb.setLocatorType(ConnectionFactoryBuilder.Locator.CONSISTENT);  
        //cfb.setHashAlg(HashAlgorithm);

        cfb.setOpTimeout(3000);
        
        List<InetSocketAddress> addresses;
        addresses = new ArrayList<InetSocketAddress>();
        addresses.add(address);

        try {
            client = new MemcachedClient(cfb.build(), addresses);
        } catch (IOException ex) {
            log.error("Could not establish a client instance");
            setAvailable(false);
        }
    }
    
    @Override
    public boolean isAvailable() {
        return available;
    }
    
    private void setAvailable(boolean available) {
        this.available = available;
    }
    
    @Override
    public Object read(String key) {
        return client.get(key);
    }

    @Override
    public void write(String key, Object value) {
        write(key, value, DEFAULT_TTL);
    }

    @Override
    public void write(String key, Object value, int ttl) {

        log.debug("Write {} / {}", key, value);

        client.set(key, ttl, value);

        //log.debug("Check value after write: {}", client.get(key));
    }

    @Override
    public void touch(String key, int ttl) {
        Object obj = client.get(key);

        if (obj != null) {
            client.set(key, ttl, obj);
        }
    }

    @Override
    public void remove(String key) {
        client.delete(key);
    }
}
