/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koobe.common.core;

import com.koobe.common.core.service.KoobeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 *
 * @author lyhcode
 */
@Configuration
@ComponentScan("com.koobe.common")
public class KoobeApplicationContext {

    private static KoobeApplication application;

    /**
     * Set application
     *
     * @param application
     */
    public static void setApplication(KoobeApplication application) {
        KoobeApplicationContext.application = application;
    }

    @Bean
    KoobeApplication application() {
        return application;
    }
}
