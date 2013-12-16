/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.koobe.common.queue;

import com.koobe.common.core.KoobeApplication;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author lyhcode
 */
public class MainTest {
    
    private static KoobeApplication application;
    private static KoobeQueueService service;
    
    public MainTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        application = new KoobeApplication();
        service = (KoobeQueueService)application.getService("koobeQueueService");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void status() {
        assertNotNull(service);
    }
        
    @Test
    public void sendAndReceiveMessage() {
        service.sendMessage("TEST-MESSAGE-BODY");
        assert(service.receiveMessages().size() > 0);
    }
}
