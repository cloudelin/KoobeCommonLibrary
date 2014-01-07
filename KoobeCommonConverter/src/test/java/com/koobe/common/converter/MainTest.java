/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.koobe.common.converter;

import com.koobe.common.core.KoobeApplication;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

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
        assertNotNull(service.getConverter(KoobeConverterType.PDF_TO_EPUB));
    }

    @Test
    public void convertWordToEpub() {

        File src = new File("ooo.doc");
        File dest = new File("ooo.epub");

        assert src.exists();
        assert !dest.exists();

        KoobeConverter converter = service.getConverter(KoobeConverterType.WORD_TO_EPUB);
        converter.convert(src, dest);

        assert (dest.exists());
    }
    

}
