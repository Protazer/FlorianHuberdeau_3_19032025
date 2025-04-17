package com.chatop.chaTop.configuration;

import com.chatop.chaTop.utils.Helpers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelpersConfig {
	@Bean
	public Helpers helpers() {
		return new Helpers();
	}
}
