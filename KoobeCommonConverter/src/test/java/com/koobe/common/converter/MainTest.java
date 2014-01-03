/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.koobe.common.converter;

import com.koobe.common.core.KoobeApplication;
import com.koobe.common.converter.impl.ConverterType;
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
    
    private KoobeApplication application;
    private KoobeConverterService service;
    
    public MainTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        application = KoobeApplication.getInstance();
        service = (KoobeConverterService)application.getService(KoobeConverterService.class);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void status() {
        assertNotNull(application);
        assertNotNull(service);
    }

    @Test
    public void getConverter() {
        assertNotNull(service.getConverter(ConverterType.PDF_TO_EPUB));
    }
    

}
