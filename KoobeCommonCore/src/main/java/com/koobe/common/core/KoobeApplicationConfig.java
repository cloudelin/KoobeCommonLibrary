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
 * Spring config class for other application context (ex. web)
 * @author cloude
 * @since 2014-1-21
 */
@Configuration
@ComponentScan(
	basePackages = {"com.koobe.common"},
	useDefaultFilters = false,
	excludeFilters = @ComponentScan.Filter(type=FilterType.ANNOTATION, value={Controller.class}),
	includeFilters = @ComponentScan.Filter(type=FilterType.ANNOTATION, value={Service.class, Repository.class, Component.class})
)
public class KoobeApplicationConfig {

	@Bean
    KoobeApplication application() {
        return KoobeApplication.getInstance(false);
    }
}
