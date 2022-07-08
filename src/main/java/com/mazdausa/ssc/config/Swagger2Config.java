package com.mazdausa.ssc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mazdausa.ssc.SSCApplication;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				// package based
				.apis(RequestHandlerSelectors.basePackage("com.mazdausa.ssc"))
				// configuration
				.paths(PathSelectors.any())
				// path based configuration
				.build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Service Shop Capacity Application") // title of our documentation
				.version("0.1") // version of the document
				.description("Swagger Implementation POC") // description for the documentation
				.termsOfServiceUrl("url for the terms of service") // terms of service and use url for the api
				.build(); // builds the apiInfo instance

	}
}
