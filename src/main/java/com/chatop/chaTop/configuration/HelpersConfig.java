package com.chatop.chaTop.configuration;

import com.chatop.chaTop.utils.Helpers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class is a configuration class that configure helpers class injection into the application.
 */
@Configuration
public class HelpersConfig {
    @Bean
    public Helpers helpers() {
        return new Helpers();
    }
}
