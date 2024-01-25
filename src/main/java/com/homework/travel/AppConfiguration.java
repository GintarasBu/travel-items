package com.homework.travel;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfiguration implements WebMvcConfigurer {

	@Override
    public void addFormatters(FormatterRegistry registry) {
        ApplicationConversionService.configure(registry);
    }

    @Bean
    MessageSource errorMessageSource() {
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasename("messages_en");
		ms.setDefaultEncoding(StandardCharsets.UTF_8.name());
		return ms;
	}
    
    @Bean
    MessageSourceAccessor messageSourceAccessor(@Autowired @Qualifier("errorMessageSource") MessageSource errorMessageSource) {
    	return new MessageSourceAccessor(errorMessageSource);
    }
}
