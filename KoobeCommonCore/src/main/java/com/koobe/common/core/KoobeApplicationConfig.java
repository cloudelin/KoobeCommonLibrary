package com.koobe.common.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring config class for other application context (ex. web)
 * @author cloude
 * @since 2014-1-21
 */
@Configuration
@ComponentScan("com.koobe.common")
public class KoobeApplicationConfig {

	@Bean
    KoobeApplication application() {
        return KoobeApplication.getInstance(false);
    }
}
