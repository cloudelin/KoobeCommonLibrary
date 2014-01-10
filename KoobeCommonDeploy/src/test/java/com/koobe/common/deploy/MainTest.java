/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.koobe.common.deploy;

import com.koobe.common.core.KoobeApplication;
import com.koobe.common.deploy.deployer.impl.BeansTalkDeployer;
import org.junit.*;

import static org.junit.Assert.assertNotNull;

/**
 * @author lyhcode
 */
public class MainTest {

    private static KoobeApplication application;
    private static KoobeDeployService service;

    public MainTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        application = KoobeApplication.getInstance();
        service = (KoobeDeployService) application.getService(KoobeDeployService.class);
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
    public void deploy() {
        BeansTalkDeployer deployer = service.getBeansTalkDeployer("KoobeQA", "koobe-test");
        assertNotNull(deployer);
    }

}
