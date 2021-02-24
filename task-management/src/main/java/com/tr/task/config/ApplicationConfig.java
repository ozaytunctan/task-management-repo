package com.tr.task.config;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tr.task.resolver.RequestLocaleResolver;

@Configuration
public class ApplicationConfig {

	
	@Value("#{'${application.internationalization.allowed.languages}'.split(',')}")
	private List<String> supportedLanguages;

	@Value("${application.internationalization.default.language}")
	private String defaultLanguage ;
	
	
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:i18n/messages");
		messageSource.setDefaultLocale(Locale.forLanguageTag("tr"));
		messageSource.setDefaultEncoding(StandardCharsets.UTF_8.toString());
		return messageSource;
	}
	
	@Bean(initMethod = "initialize")
	public LocaleResolver requestLocaleResolver() {
		RequestLocaleResolver requestLocaleResolver=new RequestLocaleResolver();
		requestLocaleResolver.setDefaultLanguage(defaultLanguage);
		requestLocaleResolver.setSupportedLanguages(supportedLanguages);
		return requestLocaleResolver;
	}
	
//	@Bean
//	public LocaleChangeInterceptor localeChangeInterceptor() {
//	    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
//	    // Defaults to "locale" if not set
//	    localeChangeInterceptor.setParamName("L");
//
//	    return localeChangeInterceptor;
//	}
	
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper=new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper;
	}
}
