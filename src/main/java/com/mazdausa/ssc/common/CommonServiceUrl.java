package com.mazdausa.ssc.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import lombok.Getter;

@Configuration
@PropertySource("classpath:/common-services.properties")
@Getter
public class CommonServiceUrl {

	@Value("${get_dlrs_rgn_url}")
	String rgn_dlr_url;
	
	@Value("${get_dlrInfo_dlrCd_url}")
	String dlrInfo_dlrCd_url;
	
	@Value("${auth_username}")
	String authUserName;
	
	@Value("${auth_password}")
	String authPass;
	
	@Value("${auth_type}")
	String authType;
	
	@Value("${auth_header_key}")
	String authHeaderKey;
	
	@Value("${auth_header_value}")
	String authHeaderValue;
	
	
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
