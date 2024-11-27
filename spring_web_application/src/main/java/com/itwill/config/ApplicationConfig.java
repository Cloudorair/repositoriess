package com.itwill.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class ApplicationConfig {
	@Bean("messageSource")
	public MessageSource messageSource() {
		ResourceBundleMessageSource resourceBundleMessageSource=
				new ResourceBundleMessageSource();
		resourceBundleMessageSource.setBasenames("messages/messages","messages/user");
		resourceBundleMessageSource.setDefaultEncoding("UTF-8");
		
		return resourceBundleMessageSource;
    }
}
