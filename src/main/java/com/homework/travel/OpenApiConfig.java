package com.homework.travel;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
@SecurityScheme(
		  type = SecuritySchemeType.HTTP,
		  name = "basicAuth",
		  scheme = "basic")
public class OpenApiConfig {

	@Bean
	OpenAPI usersOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Travel items")
						.version("1.0.0"));
	}

}
