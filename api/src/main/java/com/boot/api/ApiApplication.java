package com.boot.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(ApiApplication.class, args);

        /*String[] bean_names = applicationContext.getBeanDefinitionNames();

		for(String bean : bean_names){
			System.out.println(bean);
		}*/
	}
	@Bean
	public LocaleResolver rocaleLocaleResolver(){
		SessionLocaleResolver sessionLocaleResolver  = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.KOREA);
		return sessionLocaleResolver;
	}

}
