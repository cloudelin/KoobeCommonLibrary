/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.koobe.common.storage;

import com.koobe.common.core.KoobeApplication;
import com.koobe.common.storage.impl.KoobeStorage;
import org.junit.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * @author lyhcode
 */
public class MainTest {

    private static KoobeApplication application;

    private static KoobeStorageService service;

    private final static String DEFAULT_TEST_BUCKET = "koobe-tmp";

    public MainTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        application = KoobeApplication.getInstance();
        service = (KoobeStorageService) application.getService(KoobeStorageService.class);
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
        assertNotNull(application);
        assertNotNull(service);
    }

    @Test
    public void s3service() {
        assertNotNull(service.getS3Storage());
        assert (service.getS3Storage(DEFAULT_TEST_BUCKET).isAccessible());
    }

    @Test
    public void putFile() throws IOException {
        KoobeStorage storage = service.getS3Storage(DEFAULT_TEST_BUCKET);

        File file1 = createTempFile();
        String key = file1.getName();

        storage.putFile(key, file1);

        assert (storage.hasFile(key));


        file1 = storage.getFile(key);

        assert (file1.isFile());
        assert (file1.length() > 0);


        storage.removeFile(key);

        assertFalse(storage.hasFile(key));
    }

    private File createTempFile() throws IOException {
        File file1 = File.createTempFile("koobe-test-", ".txt");
        FileOutputStream fos = new FileOutputStream(file1);
        fos.write("test".getBytes());
        fos.close();
        return file1;
    }


}
