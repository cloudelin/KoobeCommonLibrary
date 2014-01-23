/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koobe.common.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author lyhcode
 */
@Configuration
@ComponentScan(
	basePackages = {"com.koobe.common"},
	useDefaultFilters = false,
	excludeFilters = @ComponentScan.Filter(type=FilterType.ANNOTATION, value={Controller.class}),
	includeFilters = @ComponentScan.Filter(type=FilterType.ANNOTATION, value={Service.class, Repository.class, Component.class})
)
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
