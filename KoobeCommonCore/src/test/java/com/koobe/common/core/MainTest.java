/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.koobe.common.core;

import com.koobe.common.core.config.reader.GradlePropertyConfigReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.koobe.common.core.service.KoobeService;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
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
    
    public MainTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        application = KoobeApplication.getInstance();
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testGradleConfig() throws UnsupportedEncodingException, IOException {
        String conf = "systemProp.SYSENV_AWS_ACCESS_KEY_ID=A\n";
        conf += "systemProp.SYSENV_AWS_SECRET_KEY=B";
        
        InputStream stream = new ByteArrayInputStream(conf.getBytes("UTF-8"));
        
        application.getConfig().applyConfigReader(new GradlePropertyConfigReader(stream));
        
        assertEquals(application.getConfig().get("AWS_ACCESS_KEY_ID"), "A");
        assertEquals(application.getConfig().get("AWS_SECRET_KEY"), "B");
    }

    @Test
    public void version() {
        assertNotNull(application.getVersion());
    }

    @Test
    public void listServices() {
        List<KoobeService> list1 = application.getServiceList();
        assertNotNull(list1);
        assert(list1.size() == 0);
    }


}
