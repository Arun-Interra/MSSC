package com.mazdausa.ssc.common;

import java.nio.charset.StandardCharsets;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RestTemplateConfig {
	@Autowired
	private CommonServiceUrl cmnSrvEP;

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public RestTemplate getObject() {
        return new RestTemplate();
    }
    public Class<RestTemplate> getObjectType() {
        return RestTemplate.class;
    }

    @Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public HttpHeaders getHeader(){
   	
    	String auth = cmnSrvEP.getAuthUserName()+":"+cmnSrvEP.getAuthPass();
    	byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
    	String authHeader = cmnSrvEP.getAuthType()+ new String(encodedAuth);
    	
    	HttpHeaders header = new HttpHeaders();
    	header.set("Authorization", authHeader);
		
    	return header;
    }

}
