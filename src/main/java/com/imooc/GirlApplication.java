package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Spring Boot 应用启动类
 */
@SpringBootApplication
public class GirlApplication{

	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(GirlApplication.class);
	}*/

	public static void main(String[] args) {
		SpringApplication.run(GirlApplication.class, args);
	}
}
