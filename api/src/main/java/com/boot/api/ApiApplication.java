package com.boot.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(ApiApplication.class, args);
		/*String[] bean_names = applicationContext.getBeanDefinitionNames();

		for(String bean : bean_names){
			System.out.println(bean);
		}*/
	}

}
