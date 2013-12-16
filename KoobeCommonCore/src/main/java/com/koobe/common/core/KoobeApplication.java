/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koobe.common.core;

import com.koobe.common.core.config.KoobeConfig;
import com.koobe.common.core.service.KoobeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Koobe Application context object. Implement Singleton design pattern.
 *
 * @author lyhcode
 */
public class KoobeApplication {

    // Logger
    private static final Logger log = LoggerFactory.getLogger(KoobeApplication.class);

    // Version
    private final static String VERSION = "0.1.x";

    // Singleton
    private static KoobeApplication instance = null;

    // Singleton
    private final ApplicationContext context;

    // Application Setup
    {
        // Set global application object
        KoobeApplicationContext.setApplication(this);

        // Create context
        Class appctx = KoobeApplicationContext.class;
        context = new AnnotationConfigApplicationContext(appctx);
    }

    /**
     * Always use this method to fetch a koobe application
     *
     * @return koobe application sigleton instance
     */
    public static KoobeApplication getInstance() {
        if (instance != null) {
            return instance;
        }

        return instance = new KoobeApplication();
    }

    /**
     *
     * @return application context managed by spring framework
     */
    public ApplicationContext getContext() {
        return context;
    }

    /**
     * Retrieve Koobe global config object
     *
     * @return the koobe global config object
     */
    public KoobeConfig getConfig() {
        return KoobeConfig.getInstance();
    }

    /**
     * Find koobe service bean by name
     *
     * @param serviceName specify service class name e.g. "koobeDataService"
     * @return koobe service instance
     */
    public KoobeService getService(String serviceName) {

        if (serviceName == null || serviceName.isEmpty()) {
            return null;
        }

        Object bean = context.getBean(serviceName);
        if (bean instanceof  KoobeService) {
            log.info("Find {} successful", serviceName);
            return (KoobeService)bean;
        }

        log.error("Could not find {}", serviceName);
        return null;
    }

    /**
     * Find koobe service bean by class type
     *
     * @param serviceClass specify service class type e.g. KoobeDataService.class
     * @return koobe service instance
     */
    public KoobeService getService(Class<?> serviceClass) {

        if (serviceClass == null) {
            return null;
        }

        Object bean = context.getBean(serviceClass);
        if (bean instanceof  KoobeService) {
            log.info("Find {} successful", serviceClass.getName());
            return (KoobeService)bean;
        }

        log.error("Could not find {}", serviceClass.getName());
        return null;
    }

    /**
     * Get version label
     *
     * @return version
     */
    public String getVersion() {
        return VERSION;
    }

    /**
     * Get KoobeService instances list
     *
     * @return koobe services list
     */
    public List<KoobeService> getServiceList() {
        List<KoobeService> result = new ArrayList<KoobeService>();
        String[] names = context.getBeanDefinitionNames();

        for (String name : names) {
            Object obj = context.getBean(name);
            if (obj instanceof KoobeService) {
                result.add((KoobeService)obj);
            }
        }
        return result;
    }

}
