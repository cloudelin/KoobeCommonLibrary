/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.koobe.common.cache;

import com.koobe.common.core.KoobeApplication;
import java.net.InetSocketAddress;
import org.junit.After;
import org.junit.AfterClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

/**
 * @author lyhcode
 */
public class MainTest {
    private final static String DEFAULT_HOST = "localhost";
    private final static int DEFAULT_PORT = 12999;
    
    private static KoobeApplication application;
    private static KoobeCacheService service;
    private static CacheClient client;
    private static CacheServer server;

    public MainTest() {
    }

    @BeforeClass
    public static void setUpClass() throws InterruptedException {
        application = KoobeApplication.getInstance();
        service = (KoobeCacheService) application.getService(KoobeCacheService.class);
        
        server = service.getServer(new InetSocketAddress(DEFAULT_HOST, DEFAULT_PORT));
        server.start();
        
        client = service.getClient(new InetSocketAddress(DEFAULT_HOST, DEFAULT_PORT));

    }

    @AfterClass
    public static void tearDownClass() {
        server.stop();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void status() {
        assertNotNull(application);
        assertNotNull(service);
        assertNotNull(server);
        assertNotNull(client);
        assert service.isAvailable();
        assert client.isAvailable();
    }

    @Test
    public void readAndWrite() {

        // Make random key and value
        String key = makeRndKey();
        String value = makeRndValue();

        // Write key/value pair to memcached
        client.write(key, value);
        assertEquals(value, client.read(key));
    }

    @Test
    public void loopReadAndWrite() {
        for (int i = 0; i < 999; i++) {
            // Make random key and value
            String key = makeRndKey();
            String value = makeRndValue();

            // Write key/value pair to memcached
            client.write(key, value);
            assertEquals(value, client.read(key));
        }
    }

    private String makeRndKey() {
        return "test-key-" + new Random().nextInt(65535);
    }

    private String makeRndValue() {
        return "test-key-" + new Random().nextInt(65535);
    }
}
